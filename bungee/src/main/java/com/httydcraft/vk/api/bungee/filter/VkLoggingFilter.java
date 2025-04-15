package com.httydcraft.vk.api.bungee.filter;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.bungee.BungeeVkApiPlugin;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Logging filter for VK-related messages in the Bungee environment.
 */
public class VkLoggingFilter implements Filter {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    private final BungeeVkApiPlugin plugin;
    // endregion

    // region Constructors
    /**
     * Constructs a new logging filter with the specified plugin instance.
     *
     * @param plugin the Bungee VK API plugin instance
     * @throws IllegalArgumentException if plugin is null
     */
    public VkLoggingFilter(BungeeVkApiPlugin plugin) {
        this.plugin = Preconditions.checkNotNull(plugin, "Plugin cannot be null");
        LOGGER.atFine().log("Initialized VK logging filter");
    }
    // endregion

    // region Public Methods
    /**
     * Determines if a log record is loggable based on its content and plugin configuration.
     *
     * @param logRecord the log record to evaluate
     * @return true if the record is loggable, false otherwise
     * @throws IllegalArgumentException if logRecord is null
     */
    @Override
    public boolean isLoggable(LogRecord logRecord) {
        Preconditions.checkNotNull(logRecord, "Log record cannot be null");
        String message = logRecord.getMessage();
        if (message == null) {
            LOGGER.atFine().log("Log record message is null, allowing logging");
            return true;
        }
        if (isSlf4jMessage(message)) {
            LOGGER.atFine().log("Filtered SLF4J message: %s", message);
            return false;
        }
        boolean isLoggable = plugin.getPluginConfig().isLoggingEnabled() || !isVkMessage(message);
        LOGGER.atFine().log("Log record %s: %s", isLoggable ? "allowed" : "filtered", message);
        return isLoggable;
    }
    // endregion

    // region Private Methods
    /**
     * Checks if the message is a VK-related message.
     *
     * @param message the log message to check
     * @return true if the message contains VK API URLs, false otherwise
     */
    private boolean isVkMessage(String message) {
        return message.contains("https://api.vk.com/") || message.contains("https://lp.vk.com/");
    }

    /**
     * Checks if the message is an SLF4J-related message.
     *
     * @param message the log message to check
     * @return true if the message starts with "SLF4J", false otherwise
     */
    private boolean isSlf4jMessage(String message) {
        return message.startsWith("SLF4J");
    }
    // endregion
}