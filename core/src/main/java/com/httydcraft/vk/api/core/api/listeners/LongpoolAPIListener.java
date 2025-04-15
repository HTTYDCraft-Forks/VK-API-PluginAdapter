package com.httydcraft.vk.api.core.api.listeners;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.JsonObject;
import com.httydcraft.vk.api.core.api.VkApiPlugin;
import com.httydcraft.vk.api.core.api.scheduler.CancellableRunnable;
import com.httydcraft.vk.api.core.api.scheduler.Scheduler;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.LongPollServerKeyExpiredException;
import com.vk.api.sdk.objects.callback.longpoll.responses.GetLongPollEventsResponse;
import com.vk.api.sdk.objects.groups.responses.GetLongPollServerResponse;

/**
 * VK Longpool API listener for handling VK events via polling.
 */
public class LongpoolAPIListener {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private static final int MILLI_PER_TICK = 1000 / 20;

    private VkApiPlugin plugin;

    private GetLongPollServerResponse longserver;
    private String lastTimeStamp;

    private long reconnectMillisDelay = 2;
    private boolean reconnecting = false;

    /**
     * Creates a new LongpoolAPIListener instance.
     * 
     * @param plugin the VkApiPlugin instance
     */
    public LongpoolAPIListener(VkApiPlugin plugin) {
        Preconditions.checkNotNull(plugin, "plugin cannot be null");
        this.plugin = plugin;
        this.updateLongpoolServer();
        lastTimeStamp = longserver.getTs();
        startEventListener();
        logger.atInfo().log("LongpoolAPIListener initialized");
    }

    // Event Listener
    private void startEventListener() {
        Scheduler.scheduleAtFixedRate(new CancellableRunnable() {

            public void run() {
                try {
                    handleEvents();
                } catch (ClientException e) {
                    logger.atWarning().log("Connection interrupted, will retry in %d seconds", reconnectMillisDelay);

                    reconnecting = true;
                    try {
                        Thread.sleep(reconnectMillisDelay * 1000);
                    } catch (InterruptedException ignored) {
                    }
                    reconnectMillisDelay *= 2;
                } catch (LongPollServerKeyExpiredException e) {
                    logger.atInfo().log("LongPoll key expired, updating server");
                    updateLongpoolServer();
                } catch (ApiException e) {
                    logger.atSevere().withCause(e).log("API exception, cancelling");
                    cancel();
                }
            }

            // Event Handling
            private void handleEvents() throws ApiException, ClientException {
                VkApiClient client = plugin.getVkApiProvider().getVkApiClient();
                GetLongPollEventsResponse events = client.longPoll()
                        .getEvents(longserver.getServer(), longserver.getKey(), lastTimeStamp).waitTime(10).execute();
                for (JsonObject json : events.getUpdates())
                    plugin.getVkApiProvider().getLongpoolParser().parse(json);

                lastTimeStamp = events.getTs();
                if (reconnecting) {
                    logger.atInfo().log("Connection restored!");

                    reconnectMillisDelay = 2;
                    reconnecting = false;
                }
            }

        }, 0, plugin.getPluginConfig().getLongpoolSchedulerDelay(), TimeUnit.SECONDS);
    }

    // Server Update
    private void updateLongpoolServer() {
        try {
            GroupActor actor = plugin.getVkApiProvider().getActor();
            VkApiClient client = plugin.getVkApiProvider().getVkApiClient();
            this.longserver = client.groupsLongPoll().getLongPollServer(actor, actor.getGroupId()).execute();
        } catch (ApiException | com.vk.api.sdk.exceptions.ClientException e) {
            logger.atSevere().withCause(e).log("Failed to update LongPoll server");
        }
    }
}
