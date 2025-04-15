package com.httydcraft.vk.api.bungee;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.VkApiPlugin;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.listeners.LongpoolAPIListener;
import com.httydcraft.vk.api.core.api.providers.VkApiProvider;
import com.httydcraft.vk.api.bungee.config.BungeePluginConfig;
import com.httydcraft.vk.api.bungee.filter.VkLoggingFilter;
import com.httydcraft.vk.api.bungee.providers.BungeeVkApiProvider;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Logger;

/**
 * Main plugin class for the Bungee VK API integration.
 */
public class BungeeVkApiPlugin extends Plugin implements VkApiPlugin {
    // region Fields
    private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
    private static BungeeVkApiPlugin instance;
    private BungeeVkApiProvider vkApiProvider;
    private BungeePluginConfig pluginConfig;
    // endregion

    // region Lifecycle Methods
    /**
     * Initializes the plugin on enable.
     */
    @Override
    public void onEnable() {
        instance = this;
        this.pluginConfig = new BungeePluginConfig(this);
        enableLogFiltering();
        getProxyApplier(pluginConfig.getProxyType()).apply(pluginConfig.getProxyHost(), pluginConfig.getProxyPort());
        this.vkApiProvider = new BungeeVkApiProvider(pluginConfig);
        new LongpoolAPIListener(this);
        displayStartupBanner();
        LOGGER.atInfo().log("Bungee VK API plugin enabled");
    }
    // endregion

    // region Public Methods
    /**
     * Returns the VK API provider instance.
     *
     * @return the VK API provider
     */
    @Override
    public VkApiProvider getVkApiProvider() {
        return vkApiProvider;
    }

    /**
     * Returns the plugin configuration.
     *
     * @return the plugin configuration
     */
    @Override
    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    /**
     * Calls a Bungee event.
     *
     * @param event the event to call
     */
    @Override
    public void callEvent(Object event) {
        if (!(event instanceof Event)) {
            LOGGER.atWarning().log("Attempted to call non-Event object: %s", event.getClass().getName());
            return;
        }
        Event bungeeEvent = (Event) event;
        LOGGER.atFine().log("Calling Bungee event: %s", bungeeEvent.getClass().getSimpleName());
        ProxyServer.getInstance().getPluginManager().callEvent(bungeeEvent);
    }

    /**
     * Returns the singleton instance of the plugin.
     *
     * @return the plugin instance
     */
    public static BungeeVkApiPlugin getInstance() {
        return instance;
    }
    // endregion

    // region Private Methods
    /**
     * Enables logging filter for the plugin.
     */
    private void enableLogFiltering() {
        Logger logger = getLogger();
        logger.setFilter(new VkLoggingFilter(this));
        LOGGER.atFine().log("Enabled VK logging filter");
    }

    /**
     * Displays the startup banner in the console.
     */
    private void displayStartupBanner() {
        String banner = "\r\n\r\n" +
                ChatColor.DARK_AQUA + "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
                ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA) +
                ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n" +
                ChatColor.BLUE + "██╗░░░██╗██╗░░██╗░░░░░░░█████╗░██████╗░██╗\r\n" +
                ChatColor.BLUE + "██║░░░██║██║░██╔╝░░░░░░██╔══██╗██╔══██╗██║\r\n" +
                ChatColor.BLUE + "╚██╗░██╔╝█████═╝░█████╗███████║██████╔╝██║\r\n" +
                ChatColor.BLUE + "░╚████╔╝░██╔═██╗░╚════╝██╔══██║██╔═══╝░██║\r\n" +
                ChatColor.BLUE + "░░╚██╔╝░░██║░╚██╗░░░░░░██║░░██║██║░░░░░██║\r\n" +
                ChatColor.BLUE + "░░░╚═╝░░░╚═╝░░╚═╝░░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝\r\n" +
                ChatColor.DARK_AQUA + "█████╗█████╗█████╗█████╗█████╗█████╗█████╗\r\n".replaceAll("╗",
                ChatColor.AQUA + "╗" + ChatColor.DARK_AQUA) +
                ChatColor.AQUA + "╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝╚════╝\r\n";
        System.out.println(banner);
    }
    // endregion
}