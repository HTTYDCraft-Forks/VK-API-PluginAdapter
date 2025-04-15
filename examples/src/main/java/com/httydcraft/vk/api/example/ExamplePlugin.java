package com.httydcraft.vk.api.example;

import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.example.listeners.KeyboardExample;
import com.httydcraft.vk.api.example.listeners.ScreenNameExample;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for registering VK-related listeners.
 */
public class ExamplePlugin extends JavaPlugin {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Lifecycle Methods
	/**
	 * Initializes the plugin and registers event listeners.
	 */
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new KeyboardExample(), this);
		Bukkit.getPluginManager().registerEvents(new ScreenNameExample(), this);
		LOGGER.atInfo().log("Enabled %s and registered listeners", this.getClass().getSimpleName());
	}
	// endregion
}