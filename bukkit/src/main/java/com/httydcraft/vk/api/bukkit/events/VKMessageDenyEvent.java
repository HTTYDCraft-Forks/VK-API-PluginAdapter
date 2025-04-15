package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MessageDeny;

/**
 * Event triggered when a user denies messages from a VK group.
 */
public class VKMessageDenyEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private MessageDeny messageDeny;
	// endregion

	// region Constructors
	/**
	 * Constructs a message deny event with the specified message deny object and group ID.
	 *
	 * @param messageDeny the message deny object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if messageDeny or groupId is null
	 */
	public VKMessageDenyEvent(MessageDeny messageDeny, Integer groupId) {
		super(groupId);
		setMessageDeny(messageDeny);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the message deny object associated with this event.
	 *
	 * @return the message deny object
	 */
	public MessageDeny getMessageDeny() {
		return messageDeny;
	}

	/**
	 * Returns the user ID associated with the message deny event.
	 *
	 * @return the user ID
	 */
	public Integer getUser() {
		return messageDeny.getUserId();
	}

	/**
	 * Sets the message deny object for this event.
	 *
	 * @param messageDeny the message deny object to set
	 * @throws IllegalArgumentException if messageDeny is null
	 */
	private void setMessageDeny(MessageDeny messageDeny) {
		this.messageDeny = Preconditions.checkNotNull(messageDeny, "Message deny cannot be null");
		LOGGER.atFine().log("Set message deny for %s", this.getClass().getSimpleName());
	}
	// endregion
}