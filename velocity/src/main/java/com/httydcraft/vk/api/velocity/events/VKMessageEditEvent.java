package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.messages.Message;

/**
 * Event triggered when a message is edited in a VK group.
 */
public class VKMessageEditEvent extends VKMessageUpdateEvent {
	// region Constructors
	/**
	 * Constructs a VKMessageEditEvent with the specified message and group ID.
	 *
	 * @param message the message object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if message or groupId is null
	 */
	public VKMessageEditEvent(Message message, Integer groupId) {
		super(message, groupId);
	}
	// endregion
}