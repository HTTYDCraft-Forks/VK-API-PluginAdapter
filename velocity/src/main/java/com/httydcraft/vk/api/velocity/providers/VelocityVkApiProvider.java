package com.httydcraft.vk.api.velocity.providers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.providers.AbstractVkApiProvider;
import com.httydcraft.vk.api.velocity.VelocityVkApiPlugin;
import com.httydcraft.vk.api.velocity.parsers.VelocityLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

/**
 * Provider for VK API interactions in a Velocity environment.
 */
public class VelocityVkApiProvider extends AbstractVkApiProvider {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    // endregion

    // region Constructors
    /**
     * Constructs a VelocityVkApiProvider with the specified plugin configuration.
     *
     * @param pluginConfig the plugin configuration
     * @throws IllegalArgumentException if pluginConfig is null
     */
    public VelocityVkApiProvider(PluginConfig pluginConfig) {
        super(pluginConfig, new VelocityLongpoolEventParser(VelocityVkApiPlugin.getInstance()));
        Preconditions.checkNotNull(pluginConfig, "Plugin config cannot be null");
        initializeLongPollServer();
    }
    // endregion

    // region Private Methods
    /**
     * Initializes the VK long poll server.
     */
    private void initializeLongPollServer() {
        try {
            vkApiClient.messages().getLongPollServer(groupActor).execute();
            LOGGER.atInfo().log("Successfully initialized VK long poll server");
        } catch (ApiException e) {
            LOGGER.atSevere().withCause(e).log("Failed to initialize VK long poll server. Error code: %d", e.getCode());
            VelocityVkApiPlugin.getInstance().getLogger()
                    .warn("See https://vk.com/dev/errors for details on VK error codes");
        } catch (ClientException e) {
            LOGGER.atSevere().withCause(e).log("Client error while initializing VK long poll server");
        }
    }
    // endregion
}