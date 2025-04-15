package com.httydcraft.vk.api.velocity.config;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.velocity.VelocityVkApiPlugin;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Configuration handler for the Velocity VK plugin.
 */
public class VelocityPluginConfig implements PluginConfig {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    private static final String CONFIGURATION_FILE_NAME = "config.yml";

    // Configuration keys
    private static final String SETTINGS_KEY = "settings";
    private static final String SCHEDULER_DELAY_KEY = "scheduler-delay";
    private static final String GROUP_INFO_KEY = "group-info";
    private static final String GROUP_ID_KEY = "group-id";
    private static final String GROUP_TOKEN_KEY = "group-token";
    private static final String PROXY_KEY = "proxy";
    private static final String PROXY_TYPE_KEY = "type";
    private static final String PROXY_HOST_KEY = "host";
    private static final String PROXY_PORT_KEY = "port";
    private static final String LOGGING_ENABLED_KEY = "logging-enabled";
    private static final String DEFAULT_PROXY_TYPE = "HTTP";

    private final VelocityVkApiPlugin plugin;
    private final ConfigurationNode configuration;
    private final int longpoolSchedulerDelay;
    private final int groupId;
    private final String groupToken;
    private final String proxyType;
    private final String proxyHost;
    private final int proxyPort;
    private final boolean loggingEnabled;
    // endregion

    // region Constructors
    /**
     * Constructs a new VelocityPluginConfig instance.
     *
     * @param plugin the Velocity VK plugin instance
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException    if configuration cannot be loaded
     */
    public VelocityPluginConfig(VelocityVkApiPlugin plugin) {
        Preconditions.checkNotNull(plugin, "Plugin cannot be null");
        this.plugin = plugin;
        this.configuration = loadConfiguration();
        this.longpoolSchedulerDelay = getNode(SETTINGS_KEY, SCHEDULER_DELAY_KEY).getInt(5);
        this.groupId = getNode(GROUP_INFO_KEY, GROUP_ID_KEY).getInt(0);
        this.groupToken = getNode(GROUP_INFO_KEY, GROUP_TOKEN_KEY).getString("");
        this.proxyType = getNode(PROXY_KEY, PROXY_TYPE_KEY).getString(DEFAULT_PROXY_TYPE);
        this.proxyHost = getNode(PROXY_KEY, PROXY_HOST_KEY).getString("");
        this.proxyPort = getNode(PROXY_KEY, PROXY_PORT_KEY).getInt(0);
        this.loggingEnabled = getNode(SETTINGS_KEY, LOGGING_ENABLED_KEY).getBoolean(false);
        LOGGER.atInfo().log("VelocityPluginConfig initialized successfully ^-^");
    }
    // endregion

    // region Public Methods (PluginConfig Implementation)
    /**
     * Retrieves the longpoll scheduler delay in seconds.
     *
     * @return the scheduler delay
     */
    @Override
    public int getLongpoolSchedulerDelay() {
        return longpoolSchedulerDelay;
    }

    /**
     * Retrieves the VK group ID.
     *
     * @return the group ID
     */
    @Override
    public int getGroupId() {
        return groupId;
    }

    /**
     * Retrieves the VK group token.
     *
     * @return the group token
     */
    @Override
    public String getGroupToken() {
        return groupToken;
    }

    /**
     * Retrieves the proxy type.
     *
     * @return the proxy type
     */
    @Override
    public String getProxyType() {
        return proxyType;
    }

    /**
     * Retrieves the proxy host.
     *
     * @return the proxy host
     */
    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Retrieves the proxy port.
     *
     * @return the proxy port
     */
    @Override
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * Checks if logging is enabled.
     *
     * @return true if logging is enabled, false otherwise
     */
    @Override
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }
    // endregion

    // region Private Methods
    /**
     * Loads the configuration from the plugin's data directory, copying the default if necessary.
     *
     * @return the loaded configuration node
     * @throws IllegalStateException if configuration cannot be loaded
     */
    private ConfigurationNode loadConfiguration() {
        File configFile = getBundledFile(CONFIGURATION_FILE_NAME);
        try {
            return YAMLConfigurationLoader.builder().setFile(configFile).build().load();
        } catch (IOException e) {
            LOGGER.atSevere().withCause(e).log("Failed to load configuration file: %s", configFile.getPath());
            throw new IllegalStateException("Cannot load configuration", e);
        }
    }

    /**
     * Retrieves or creates the configuration file, copying the default from resources if it doesn't exist.
     *
     * @param name the name of the configuration file
     * @return the configuration file
     * @throws IllegalStateException if the file cannot be created or copied
     */
    private File getBundledFile(String name) {
        Path pluginDir = plugin.getPluginPath();
        File file = new File(pluginDir.toFile(), name);

        if (!file.exists()) {
            try {
                Files.createDirectories(pluginDir);
                try (InputStream in = VelocityVkApiPlugin.class.getResourceAsStream("/" + name)) {
                    if (in == null) {
                        LOGGER.atSevere().log("Resource %s not found in plugin JAR", name);
                        throw new IllegalStateException("Resource " + name + " not found");
                    }
                    Files.copy(in, file.toPath());
                    LOGGER.atFine().log("Copied default configuration file: %s", name);
                }
            } catch (IOException e) {
                LOGGER.atSevere().withCause(e).log("Failed to copy default configuration file: %s", name);
                throw new IllegalStateException("Cannot copy default configuration", e);
            }
        }

        return file;
    }

    /**
     * Retrieves a configuration node for the specified path.
     *
     * @param path the path to the node
     * @return the configuration node
     */
    private ConfigurationNode getNode(String... path) {
        return configuration.getNode((Object[]) path);
    }
    // endregion
}