package com.httydcraft.vk.api.bukkit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.VkApiPlugin;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.listeners.LongpoolAPIListener;
import com.httydcraft.vk.api.core.api.providers.VkApiProvider;
import com.httydcraft.vk.api.bukkit.config.BukkitPluginConfig;
import com.httydcraft.vk.api.bukkit.filters.LogFilter;
import com.httydcraft.vk.api.bukkit.providers.BukkitVkApiProvider;

import net.md_5.bungee.api.ChatColor;

/**
 * Main Bukkit plugin class for VK integration.
 */
public class BukkitVkApiPlugin extends JavaPlugin implements VkApiPlugin {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    private BukkitVkApiProvider vkApiProvider;
    private BukkitPluginConfig pluginConfig;

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        Logger coreLogger = (Logger) LogManager.getRootLogger();
        pluginConfig = new BukkitPluginConfig(this);
        coreLogger.addFilter(new LogFilter(this));
        Preconditions.checkNotNull(pluginConfig, "pluginConfig cannot be null");
        Preconditions.checkNotNull(coreLogger, "coreLogger cannot be null");
        getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
        vkApiProvider = new BukkitVkApiProvider(pluginConfig);
        new LongpoolAPIListener(this);
        logger.atInfo().log("BukkitVkApiPlugin enabled");
        Bukkit.getConsoleSender()
                .sendMessage("\n\r" + ChatColor.BLUE + " ##      ## ##   ##           ##     #######  ##\r\n"
                        + ChatColor.BLUE + "/##     /##/##  ##           ####   /##////##/##\r\n" + ChatColor.BLUE
                        + "/##     /##/## ##           ##//##  /##   /##/##\r\n" + ChatColor.BLUE
                        + "//##    ## /####    #####  ##  //## /####### /##\r\n" + ChatColor.BLUE
                        + " //##  ##  /##/##  /////  ##########/##////  /##\r\n" + ChatColor.BLUE
                        + "  //####   /##//##       /##//////##/##      /##\r\n" + ChatColor.BLUE
                        + "   //##    /## //##      /##     /##/##      /##\r\n" + ChatColor.BLUE
                        + "    //     //   //       //      // //       // \r\n");
    }

    /**
     * Returns the VK API provider instance.
     * 
     * @return the VK API provider instance
     */
    @Override
    public VkApiProvider getVkApiProvider() {
        return vkApiProvider;
    }

    /**
     * Returns the plugin configuration instance.
     * 
     * @return the plugin configuration instance
     */
    @Override
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    /**
     * Calls an event on the Bukkit event bus.
     * 
     * @param event the event to call
     */
    @Override
    public void callEvent(Object event) {
        if (!(event instanceof Event))
            return;
        Event bukkitEvent = (Event) event;
        Bukkit.getPluginManager().callEvent(bukkitEvent);
    }
}
