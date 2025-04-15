package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.MessageDeny;

/**
 * Event triggered when a user denies messages from a VK group.
 */
public class VKMessageDenyEvent extends AbstractVkEvent {
	// region Fields
	private MessageDeny messageDeny;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMessageDenyEvent with the specified message deny and group ID.
	 *
	 * @param messageDeny the message deny object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if messageDeny or groupId is null
	 */
	public VKMessageDenyEvent(MessageDeny messageDeny, Integer groupId) {
		super(groupId);
		setMessageDeny(messageDeny);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the message deny associated with this event.
	 *
	 * @return the message deny object
	 */
	public MessageDeny getMessageDeny() {
		return messageDeny;
	}

	/**
	 * Retrieves the user ID associated with this event.
	 *
	 * @return the user ID
	 */
	public Integer getUser() {
		return messageDeny.getUserId();
	}

	/**
	 * Sets the message deny for this event.
	 *
	 * @param messageDeny the message deny object
	 * @throws IllegalArgumentException if messageDeny is null
	 */
	private void setMessageDeny(MessageDeny messageDeny) {
		Preconditions.checkNotNull(messageDeny, "Message deny cannot be null");
		this.messageDeny = messageDeny;
	}
	// endregion
}