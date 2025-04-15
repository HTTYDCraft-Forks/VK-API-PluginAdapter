package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.MessageAllow;

/**
 * Event triggered when a user allows messages from a VK group.
 */
public class VKMessageAllowEvent extends AbstractVkEvent {
	// region Fields
	private MessageAllow messageAllow;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMessageAllowEvent with the specified message allow and group ID.
	 *
	 * @param messageAllow the message allow object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if messageAllow or groupId is null
	 */
	public VKMessageAllowEvent(MessageAllow messageAllow, Integer groupId) {
		super(groupId);
		setMessageAllow(messageAllow);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the message allow associated with this event.
	 *
	 * @return the message allow object
	 */
	public MessageAllow getMessageAllow() {
		return messageAllow;
	}

	/**
	 * Retrieves the user ID associated with this event.
	 *
	 * @return the user ID
	 */
	public Integer getUser() {
		return messageAllow.getUserId();
	}

	/**
	 * Sets the message allow for this event.
	 *
	 * @param messageAllow the message allow object
	 * @throws IllegalArgumentException if messageAllow is null
	 */
	private void setMessageAllow(MessageAllow messageAllow) {
		Preconditions.checkNotNull(messageAllow, "Message allow cannot be null");
		this.messageAllow = messageAllow;
	}
	// endregion
}