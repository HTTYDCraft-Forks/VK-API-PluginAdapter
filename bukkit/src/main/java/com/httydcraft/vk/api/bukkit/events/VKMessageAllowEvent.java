package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MessageAllow;

/**
 * Event triggered when a user allows messages from a VK group.
 */
public class VKMessageAllowEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private MessageAllow messageAllow;
	// endregion

	// region Constructors
	/**
	 * Constructs a message allow event with the specified message allow object and group ID.
	 *
	 * @param messageAllow the message allow object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if messageAllow or groupId is null
	 */
	public VKMessageAllowEvent(MessageAllow messageAllow, Integer groupId) {
		super(groupId);
		setMessageAllow(messageAllow);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the message allow object associated with this event.
	 *
	 * @return the message allow object
	 */
	public MessageAllow getMessageAllow() {
		return messageAllow;
	}

	/**
	 * Returns the user ID associated with the message allow event.
	 *
	 * @return the user ID
	 */
	public Integer getUser() {
		return messageAllow.getUserId();
	}

	/**
	 * Sets the message allow object for this event.
	 *
	 * @param messageAllow the message allow object to set
	 * @throws IllegalArgumentException if messageAllow is null
	 */
	private void setMessageAllow(MessageAllow messageAllow) {
		this.messageAllow = Preconditions.checkNotNull(messageAllow, "Message allow cannot be null");
		LOGGER.atFine().log("Set message allow for %s", this.getClass().getSimpleName());
	}
	// endregion
}