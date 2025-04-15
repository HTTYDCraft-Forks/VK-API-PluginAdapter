package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.bungee.BungeeVkApiPlugin;
import com.httydcraft.vk.api.core.event.VkGroupEvent;
import net.md_5.bungee.api.plugin.Event;

/**
 * Abstract base class for VK-related events in the Bungee environment.
 */
public abstract class AbstractVkEvent extends Event implements VkGroupEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private static final BungeeVkApiPlugin PLUGIN = BungeeVkApiPlugin.getInstance();
	private final Integer groupId;
	// endregion

	// region Constructors
	/**
	 * Constructs a VK event with the specified group ID.
	 *
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if groupId is null
	 */
	public AbstractVkEvent(Integer groupId) {
		this.groupId = Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the ID of the VK group associated with this event.
	 *
	 * @return the group ID
	 */
	@Override
	public int groupId() {
		return groupId;
	}

	/**
	 * Calls the event using the plugin's event manager.
	 */
	public void callEvent() {
		LOGGER.atFine().log("Calling event: %s", this.getClass().getSimpleName());
		PLUGIN.callEvent(this);
	}
	// endregion
}