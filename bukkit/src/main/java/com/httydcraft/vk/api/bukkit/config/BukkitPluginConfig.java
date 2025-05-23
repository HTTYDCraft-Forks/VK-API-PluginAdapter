package com.httydcraft.vk.api.bukkit.config;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import org.bukkit.configuration.ConfigurationSection;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;

import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.bukkit.BukkitVkApiPlugin;

/**
 * Configuration handler for Bukkit VK plugin.
 */
public class BukkitPluginConfig implements PluginConfig {

    // Configuration
    private ConfigurationSection configuration;
    private int longpoolSchedulerDelay, groupId, proxyPort;
    private String groupToken, proxyType, proxyHost;
    private boolean loggingEnabled;

    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Creates a new instance of the configuration handler.
     * 
     * @param plugin the plugin instance
     */
    public BukkitPluginConfig(BukkitVkApiPlugin plugin) {
        Preconditions.checkNotNull(plugin, "plugin cannot be null");
        plugin.saveDefaultConfig();
        configuration = plugin.getConfig();
        longpoolSchedulerDelay = getInt(SETTINGS_KEY, SCHEDULER_DELAY_KEY);
        groupId = getInt(DEFAULT_GROUP_ID, GROUP_INFO_KEY, GROUP_ID_KEY);
        groupToken = getString(GROUP_INFO_KEY, GROUP_TOKEN_KEY);
        proxyType = getStringDefault(DEFAULT_PORT_TYPE, PROXY_KEY, PROXY_TYPE_KEY);
        proxyHost = getString(PROXY_KEY, PROXY_HOST_KEY);
        proxyPort = getInt(PROXY_KEY, PROXY_PORT_KEY);
        loggingEnabled = getBoolean(SETTINGS_KEY, LOGGING_ENABLED_KEY);
        logger.atInfo().log("BukkitPluginConfig initialized");
    }

    // Getters
    @Override
    public int getLongpoolSchedulerDelay() {
        return longpoolSchedulerDelay;
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    @Override
    public String getGroupToken() {
        return groupToken;
    }

    @Override
    public String getProxyType() {
        return proxyType;
    }

    @Override
    public String getProxyHost() {
        return proxyHost;
    }

    @Override
    public int getProxyPort() {
        return proxyPort;
    }

    @Override
    public boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    // Helper methods
    private boolean getBoolean(String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getBoolean(key)), path).orElse(false);
    }

    private String getString(String... path) {
        return getStringDefault("", path);
    }

    private String getStringDefault(String def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getString(key)), path).orElse(def);
    }

    private int getInt(String... path) {
        return getInt(-1, path);
    }

    private int getInt(int def, String... path) {
        return getSection((sectionOptional, key) -> sectionOptional.map(section -> section.getInt(key)), path).orElse(def);
    }

    private <T> Optional<T> getSection(BiFunction<Optional<ConfigurationSection>, String, Optional<T>> mapper, String... path) {
        int limit = path.length - 1;
        String valueKey = path[limit];
        return mapper.apply(Optional.ofNullable(
                Arrays.stream(path).limit(limit).reduce(configuration, ConfigurationSection::getConfigurationSection, (first, second) -> first)), valueKey);
    }
}
