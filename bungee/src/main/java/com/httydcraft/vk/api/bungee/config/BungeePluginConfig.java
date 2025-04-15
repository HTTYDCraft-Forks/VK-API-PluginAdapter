package com.httydcraft.vk.api.bungee.config;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.bungee.BungeeVkApiPlugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * Configuration handler for the Bungee VK API plugin.
 */
public class BungeePluginConfig implements PluginConfig {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    private final Configuration configuration;
    private final int longpoolSchedulerDelay;
    private final int groupId;
    private final int proxyPort;
    private final String groupToken;
    private final String proxyType;
    private final String proxyHost;
    private final boolean loggingEnabled;
    // endregion

    // region Constructors
    /**
     * Constructs a new configuration instance for the Bungee VK API plugin.
     *
     * @param plugin the Bungee VK API plugin instance
     * @throws IllegalArgumentException if plugin is null
     * @throws IllegalStateException if configuration loading fails
     */
    public BungeePluginConfig(BungeeVkApiPlugin plugin) {
        Preconditions.checkNotNull(plugin, "Plugin cannot be null");
        this.configuration = loadConfiguration(
                Preconditions.checkNotNull(plugin.getDataFolder(), "Data folder cannot be null"),
                Preconditions.checkNotNull(plugin.getResourceAsStream("config.yml"), "Config resource cannot be null"));
        Preconditions.checkNotNull(configuration, "Configuration cannot be null");
        this.longpoolSchedulerDelay = getInt(SETTINGS_KEY, SCHEDULER_DELAY_KEY);
        this.groupId = getInt(GROUP_INFO_KEY, GROUP_ID_KEY);
        this.groupToken = getString(GROUP_INFO_KEY, GROUP_TOKEN_KEY);
        this.proxyType = getStringDefault(DEFAULT_PORT_TYPE, PROXY_KEY, PROXY_TYPE_KEY);
        this.proxyHost = getString(PROXY_KEY, PROXY_HOST_KEY);
        this.proxyPort = getInt(PROXY_KEY, PROXY_PORT_KEY);
        this.loggingEnabled = getBoolean(SETTINGS_KEY, LOGGING_ENABLED_KEY);
        LOGGER.atInfo().log("Initialized configuration for plugin");
    }
    // endregion

    // region Public Methods
    /**
     * Returns the delay for the longpoll scheduler.
     *
     * @return the longpoll scheduler delay
     */
    @Override
    public int getLongpoolSchedulerDelay() {
        return longpoolSchedulerDelay;
    }

    /**
     * Returns the VK group ID.
     *
     * @return the group ID
     */
    @Override
    public int getGroupId() {
        return groupId;
    }

    /**
     * Returns the VK group token.
     *
     * @return the group token
     */
    @Override
    public String getGroupToken() {
        return groupToken;
    }

    /**
     * Returns the proxy type.
     *
     * @return the proxy type
     */
    @Override
    public String getProxyType() {
        return proxyType;
    }

    /**
     * Returns the proxy host.
     *
     * @return the proxy host
     */
    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * Returns the proxy port.
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
     * Retrieves a boolean value from the configuration.
     *
     * @param path the configuration path
     * @return the boolean value, or false if not found
     */
    private boolean getBoolean(String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getBoolean(key)), path)
                .orElse(false);
    }

    /**
     * Retrieves a string value from the configuration.
     *
     * @param path the configuration path
     * @return the string value, or empty string if not found
     */
    private String getString(String... path) {
        return getStringDefault("", path);
    }

    /**
     * Retrieves a string value from the configuration with a default value.
     *
     * @param def  the default value
     * @param path the configuration path
     * @return the string value, or the default if not found
     */
    private String getStringDefault(String def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getString(key)), path)
                .orElse(def);
    }

    /**
     * Retrieves an integer value from the configuration with a default value.
     *
     * @param def  the default value
     * @param path the configuration path
     * @return the integer value, or the default if not found
     */
    private int getInt(int def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getInt(key)), path)
                .orElse(def);
    }

    /**
     * Retrieves an integer value from the configuration.
     *
     * @param path the configuration path
     * @return the integer value, or -1 if not found
     */
    private int getInt(String... path) {
        return getInt(-1, path);
    }

    /**
     * Retrieves a configuration section value using a mapper function.
     *
     * @param mapper the function to map the section and key to a value
     * @param path   the configuration path
     * @param <T>    the type of the value
     * @return an Optional containing the value, or empty if not found
     */
    private <T> Optional<T> getSection(BiFunction<Optional<Configuration>, String, Optional<T>> mapper, String... path) {
        Preconditions.checkNotNull(path, "Path cannot be null");
        Preconditions.checkArgument(path.length > 0, "Path cannot be empty");
        int limit = path.length - 1;
        String valueKey = path[limit];
        Configuration section = Arrays.stream(path)
                .limit(limit)
                .reduce(configuration, Configuration::getSection, (first, second) -> first);
        return mapper.apply(Optional.ofNullable(section), valueKey);
    }

    /**
     * Loads the configuration from a file or resource.
     *
     * @param folder           the plugin data folder
     * @param resourceAsStream the config resource stream
     * @return the loaded configuration
     * @throws IllegalStateException if configuration loading fails
     */
    private Configuration loadConfiguration(File folder, InputStream resourceAsStream) {
        Preconditions.checkNotNull(folder, "Folder cannot be null");
        Preconditions.checkNotNull(resourceAsStream, "Resource stream cannot be null");
        try {
            if (!folder.exists()) {
                folder.mkdirs();
                LOGGER.atFine().log("Created plugin data folder: %s", folder.getPath());
            }

            File configFile = new File(folder, "config.yml");
            if (!configFile.exists()) {
                Files.copy(resourceAsStream, configFile.toPath());
                LOGGER.atFine().log("Copied default config to: %s", configFile.getPath());
            }

            Configuration defaults = ConfigurationProvider.getProvider(YamlConfiguration.class).load(resourceAsStream);
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile, defaults);
            LOGGER.atFine().log("Loaded configuration from: %s", configFile.getPath());
            return config;
        } catch (IOException e) {
            LOGGER.atSevere().withCause(e).log("Failed to load configuration");
            throw new IllegalStateException("Could not load configuration", e);
        }
    }
    // endregion
}