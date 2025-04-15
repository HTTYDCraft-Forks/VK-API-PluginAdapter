package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.messages.Message;

/**
 * Event triggered when a message is edited in a VK group.
 */
public class VKMessageEditEvent extends VKMessageUpdateEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a message edit event with the specified message and group ID.
	 *
	 * @param message the edited message
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if message or groupId is null
	 */
	public VKMessageEditEvent(Message message, Integer groupId) {
		super(message, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}