package com.httydcraft.vk.api.velocity;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.inject.Inject;
import com.httydcraft.vk.api.core.api.VkApiPlugin;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.listeners.LongpoolAPIListener;
import com.httydcraft.vk.api.core.api.providers.VkApiProvider;
import com.httydcraft.vk.api.velocity.config.VelocityPluginConfig;
import com.httydcraft.vk.api.velocity.parsers.VelocityLongpoolEventParser;
import com.httydcraft.vk.api.velocity.providers.VelocityVkApiProvider;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * Main plugin class for VK API integration in a Velocity environment.
 */
@Plugin(id = "vk-api", name = "VK-API", version = "0.4.1", authors = "Ubivashka")
public class VelocityVkApiPlugin implements VkApiPlugin {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    private static VelocityVkApiPlugin instance;

    private final ProxyServer proxyServer;
    private final Logger logger;
    private final Path pluginPath;
    private VelocityPluginConfig pluginConfig;
    private VelocityLongpoolEventParser longpoolEventParser;
    private VelocityVkApiProvider velocityVkApiProvider;
    // endregion

    // region Constructors
    /**
     * Constructs a VelocityVkApiPlugin with the specified dependencies.
     *
     * @param proxyServer the Velocity proxy server
     * @param logger      the SLF4J logger
     * @param pluginPath  the plugin data directory
     * @throws IllegalArgumentException if any parameter is null
     */
    @Inject
    public VelocityVkApiPlugin(ProxyServer proxyServer, Logger logger, @DataDirectory Path pluginPath) {
        Preconditions.checkNotNull(proxyServer, "Proxy server cannot be null");
        Preconditions.checkNotNull(logger, "Logger cannot be null");
        Preconditions.checkNotNull(pluginPath, "Plugin path cannot be null");
        instance = this;
        this.proxyServer = proxyServer;
        this.logger = logger;
        this.pluginPath = pluginPath;
        LOGGER.atInfo().log("Initialized VelocityVkApiPlugin instance");
    }
    // endregion

    // region Event Handlers
    /**
     * Handles the proxy initialization event to set up the plugin.
     *
     * @param event the proxy initialization event
     */
    @Subscribe
    public void onEnable(ProxyInitializeEvent event) {
        this.pluginConfig = new VelocityPluginConfig(this);
        Preconditions.checkNotNull(pluginConfig, "Plugin config cannot be null after initialization");
        getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
        LOGGER.atFine().log("Applied proxy settings: type=%s, host=%s, port=%d",
                pluginConfig.getProxyType(), pluginConfig.getProxyHost(), pluginConfig.getProxyPort());

        this.longpoolEventParser = new VelocityLongpoolEventParser(this);
        this.velocityVkApiProvider = new VelocityVkApiProvider(pluginConfig);
        new LongpoolAPIListener(this);
        LOGGER.atInfo().log("Plugin enabled successfully ^-^");
    }
    // endregion

    // region Public Methods
    /**
     * Retrieves the VK API provider.
     *
     * @return the VK API provider
     */
    @Override
    public VkApiProvider getVkApiProvider() {
        return velocityVkApiProvider;
    }

    /**
     * Retrieves the plugin configuration.
     *
     * @return the plugin configuration
     */
    @Override
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    /**
     * Calls an event using the proxy server's event manager.
     *
     * @param event the event to call
     * @throws IllegalArgumentException if event is null
     */
    @Override
    public void callEvent(Object event) {
        Preconditions.checkNotNull(event, "Event cannot be null");
        proxyServer.getEventManager().fire(event);
        LOGGER.atFine().log("Fired event: %s", event.getClass().getSimpleName());
    }

    /**
     * Retrieves the Velocity proxy server.
     *
     * @return the proxy server
     */
    public ProxyServer getProxyServer() {
        return proxyServer;
    }

    /**
     * Retrieves the SLF4J logger.
     *
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Retrieves the plugin data directory path.
     *
     * @return the plugin path
     */
    public Path getPluginPath() {
        return pluginPath;
    }

    /**
     * Retrieves the singleton instance of the plugin.
     *
     * @return the plugin instance
     */
    public static VelocityVkApiPlugin getInstance() {
        Preconditions.checkState(instance != null, "Plugin instance not initialized");
        return instance;
    }
    // endregion
}