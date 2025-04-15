package com.httydcraft.vk.api.bukkit.providers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.providers.AbstractVkApiProvider;
import com.httydcraft.vk.api.bukkit.BukkitVkApiPlugin;
import com.httydcraft.vk.api.bukkit.parsers.BukkitLongpoolEventParser;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;

import net.md_5.bungee.api.ChatColor;

/**
 * Bukkit implementation of the VK API provider.
 */
public class BukkitVkApiProvider extends AbstractVkApiProvider {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Creates a new instance of the Bukkit VK API provider.
     * 
     * @param pluginConfig the plugin configuration
     */
    public BukkitVkApiProvider(PluginConfig pluginConfig) {
        super(Preconditions.checkNotNull(pluginConfig, "pluginConfig cannot be null"), new BukkitLongpoolEventParser(
                BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class)));

        try {
            vkApiClient.messages().getLongPollServer(groupActor).execute();
        } catch(ApiException e) {
            logger.atSevere().withCause(e).log("VK API error: %d", e.getCode());
            System.out.println(ChatColor.RED + "Код ошибки: " + e.getCode());
            System.out.println(ChatColor.RED + "В сайте https://vk.com/dev/errors описаны все ошибки связанные с ВК");
        } catch(ClientException e) {
            logger.atSevere().withCause(e).log("Client exception");
        }
    }
}