package com.httydcraft.vk.api.bungee.providers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.providers.AbstractVkApiProvider;
import com.httydcraft.vk.api.bungee.BungeeVkApiPlugin;
import com.httydcraft.vk.api.bungee.parsers.BungeeLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

/**
 * Provider for VK API integration in a Bungee environment.
 */
public class BungeeVkApiProvider extends AbstractVkApiProvider {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    // endregion

    // region Constructors
    /**
     * Constructs a new VK API provider with the specified plugin configuration.
     *
     * @param pluginConfig the plugin configuration
     * @throws IllegalArgumentException if pluginConfig is null
     * @throws IllegalStateException if longpoll server initialization fails
     */
    public BungeeVkApiProvider(PluginConfig pluginConfig) {
        super(
                Preconditions.checkNotNull(pluginConfig, "Plugin config cannot be null"),
                new BungeeLongpoolEventParser(
                        Preconditions.checkNotNull(BungeeVkApiPlugin.getInstance(), "Plugin instance cannot be null")
                )
        );
        initializeLongPollServer();
        LOGGER.atInfo().log("Initialized VK API provider");
    }
    // endregion

    // region Private Methods
    /**
     * Initializes the VK longpoll server connection.
     *
     * @throws IllegalStateException if initialization fails
     */
    private void initializeLongPollServer() {
        try {
            vkApiClient.messages().getLongPollServer(groupActor).execute();
            LOGGER.atFine().log("Successfully initialized longpoll server");
        } catch (ApiException e) {
            LOGGER.atSevere().withCause(e).log("VK API error: Code %d. See https://vk.com/dev/errors for details", e.getCode());
            throw new IllegalStateException("Failed to initialize longpoll server", e);
        } catch (ClientException e) {
            LOGGER.atSevere().withCause(e).log("Client error during longpoll server initialization");
            throw new IllegalStateException("Failed to initialize longpoll server", e);
        }
    }
    // endregion
}