package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.event.VkGroupEvent;
import com.httydcraft.vk.api.velocity.VelocityVkApiPlugin;

/**
 * Abstract base class for VK group events.
 */
public abstract class AbstractVkEvent implements VkGroupEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private static final VelocityVkApiPlugin PLUGIN = VelocityVkApiPlugin.getInstance();
	private final Integer groupId;
	// endregion

	// region Constructors
	/**
	 * Constructs an AbstractVkEvent with the specified group ID.
	 *
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if groupId is null
	 */
	public AbstractVkEvent(Integer groupId) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		this.groupId = groupId;
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the group ID associated with this event.
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
		LOGGER.atFine().log("Calling event: %s for group ID: %d", this.getClass().getSimpleName(), groupId);
		PLUGIN.callEvent(this);
	}
	// endregion
}