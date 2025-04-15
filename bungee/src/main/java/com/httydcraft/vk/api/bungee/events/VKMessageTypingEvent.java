package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.parsers.objects.MessageTyping;

/**
 * Event triggered when a user is typing a message in a VK group.
 */
public class VKMessageTypingEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private MessageTyping messageTyping;
	// endregion

	// region Constructors
	/**
	 * Constructs a message typing event with the specified typing info and group ID.
	 *
	 * @param messageTyping the message typing object
	 * @param groupId       the ID of the VK group
	 * @throws IllegalArgumentException if messageTyping or groupId is null
	 */
	public VKMessageTypingEvent(MessageTyping messageTyping, Integer groupId) {
		super(groupId);
		setMessageTyping(messageTyping);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the message typing information.
	 *
	 * @return the message typing object
	 */
	public MessageTyping getMessageTyping() {
		return messageTyping;
	}

	/**
	 * Returns the state of the typing event.
	 *
	 * @return the typing state
	 */
	public String getState() {
		return messageTyping.getState();
	}

	/**
	 * Returns the recipient ID of the typing event.
	 *
	 * @return the recipient ID
	 */
	public Integer getToId() {
		return messageTyping.getToId();
	}

	/**
	 * Returns the sender ID of the typing event.
	 *
	 * @return the sender ID
	 */
	public Integer getFromId() {
		return messageTyping.getFromId();
	}

	/**
	 * Sets the message typing information.
	 *
	 * @param messageTyping the message typing object to set
	 * @throws IllegalArgumentException if messageTyping is null
	 */
	public void setMessageTyping(MessageTyping messageTyping) {
		this.messageTyping = Preconditions.checkNotNull(messageTyping, "Message typing cannot be null");
		LOGGER.atFine().log("Set message typing for %s", this.getClass().getSimpleName());
	}
	// endregion
}