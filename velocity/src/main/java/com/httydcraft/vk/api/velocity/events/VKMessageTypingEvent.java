package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.httydcraft.vk.api.core.api.parsers.objects.MessageTyping;

/**
 * Event triggered when a user is typing a message in a VK group.
 */
public class VKMessageTypingEvent extends AbstractVkEvent {
	// region Fields
	private MessageTyping messageTyping;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMessageTypingEvent with the specified message typing and group ID.
	 *
	 * @param messageTyping the message typing object
	 * @param groupId       the ID of the VK group
	 * @throws IllegalArgumentException if messageTyping or groupId is null
	 */
	public VKMessageTypingEvent(MessageTyping messageTyping, Integer groupId) {
		super(groupId);
		setMessageTyping(messageTyping);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the message typing associated with this event.
	 *
	 * @return the message typing object
	 */
	public MessageTyping getMessageTyping() {
		return messageTyping;
	}

	/**
	 * Retrieves the typing state.
	 *
	 * @return the typing state
	 */
	public String getState() {
		return messageTyping.getState();
	}

	/**
	 * Retrieves the ID of the recipient.
	 *
	 * @return the recipient ID
	 */
	public Integer getToId() {
		return messageTyping.getToId();
	}

	/**
	 * Retrieves the ID of the user typing.
	 *
	 * @return the user ID
	 */
	public Integer getFromId() {
		return messageTyping.getFromId();
	}

	/**
	 * Sets the message typing for this event.
	 *
	 * @param messageTyping the message typing object
	 * @throws IllegalArgumentException if messageTyping is null
	 */
	public void setMessageTyping(MessageTyping messageTyping) {
		Preconditions.checkNotNull(messageTyping, "Message typing cannot be null");
		this.messageTyping = messageTyping;
	}
	// endregion
}