package com.httydcraft.vk.api.bukkit.filters;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;

import com.httydcraft.vk.api.bukkit.BukkitVkApiPlugin;

/**
 * Bukkit log filter for VK plugin events.
 */
public class LogFilter implements Filter {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private final BukkitVkApiPlugin plugin;

    /**
     * Creates a new LogFilter instance.
     * 
     * @param plugin the plugin instance
     */
    public LogFilter(BukkitVkApiPlugin plugin) {
        Preconditions.checkNotNull(plugin, "plugin cannot be null");
        this.plugin = plugin;
        logger.atInfo().log("LogFilter initialized");
    }

    /**
     * Checks if the log message should be denied.
     * 
     * @param message the log message
     * @return the filter result
     */
    public Filter.Result checkMessage(String message) {
        Preconditions.checkNotNull(message, "message cannot be null");
        if (plugin.getPluginConfig().isLoggingEnabled())
            if (message.startsWith("Request: https://api.vk.com/") || message.startsWith("Request: https://lp.vk.com/")
                    || message.startsWith("ERROR StatusLogger") || message.contains("lp.vk.com")
                    || message.contains("Nag author: 'Ubivashka' of 'VK-API'")) {
                logger.atInfo().log("Denied log message: %s", message);
                return Filter.Result.DENY;
            }
        return Result.NEUTRAL;
    }

    /**
     * Gets the state of the filter.
     * 
     * @return the state
     */
    public LifeCycle.State getState() {
        try {
            return LifeCycle.State.STARTED;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * Initializes the filter.
     */
    public void initialize() {
    }

    /**
     * Checks if the filter is started.
     * 
     * @return true if started, false otherwise
     */
    public boolean isStarted() {
        return true;
    }

    /**
     * Checks if the filter is stopped.
     * 
     * @return true if stopped, false otherwise
     */
    public boolean isStopped() {
        return false;
    }

    /**
     * Starts the filter.
     */
    public void start() {
    }

    /**
     * Stops the filter.
     */
    public void stop() {
    }

    /**
     * Filters the log event.
     * 
     * @param event the log event
     * @return the filter result
     */
    @Override
    public Filter.Result filter(LogEvent event) {
        return checkMessage(event.getMessage().getFormattedMessage());
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameters
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object... arg4) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the throwable
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Object message, Throwable arg4) {
        return checkMessage(message.toString());
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the throwable
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Message message, Throwable arg4) {
        return checkMessage(message.getFormattedMessage());
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @param arg9 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8, Object arg9) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @param arg9 the parameter
     * @param arg10 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8, Object arg9, Object arg10) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @param arg9 the parameter
     * @param arg10 the parameter
     * @param arg11 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @param arg9 the parameter
     * @param arg10 the parameter
     * @param arg11 the parameter
     * @param arg12 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12) {
        return checkMessage(message);
    }

    /**
     * Filters the log event.
     * 
     * @param arg0 the logger
     * @param arg1 the level
     * @param arg2 the marker
     * @param message the log message
     * @param arg4 the parameter
     * @param arg5 the parameter
     * @param arg6 the parameter
     * @param arg7 the parameter
     * @param arg8 the parameter
     * @param arg9 the parameter
     * @param arg10 the parameter
     * @param arg11 the parameter
     * @param arg12 the parameter
     * @param arg13 the parameter
     * @return the filter result
     */
    @Override
    public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5,
            Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12,
            Object arg13) {
        return checkMessage(message);
    }

    /**
     * Gets the result when the filter matches.
     * 
     * @return the result
     */
    @Override
    public Filter.Result getOnMatch() {
        return Filter.Result.NEUTRAL;
    }

    /**
     * Gets the result when the filter does not match.
     * 
     * @return the result
     */
    @Override
    public Filter.Result getOnMismatch() {
        return Filter.Result.NEUTRAL;
    }
}
