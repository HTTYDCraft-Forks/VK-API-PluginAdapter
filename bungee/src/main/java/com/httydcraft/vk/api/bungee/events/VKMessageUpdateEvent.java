package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.messages.Message;
import net.md_5.bungee.api.plugin.Cancellable;

/**
 * Abstract base class for message update events in a VK group.
 */
public abstract class VKMessageUpdateEvent extends AbstractVkEvent implements Cancellable {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private boolean cancel = false;
	private Message message;
	// endregion

	// region Constructors
	/**
	 * Constructs a message update event with the specified message and group ID.
	 *
	 * @param message the message object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if message or groupId is null
	 */
	public VKMessageUpdateEvent(Message message, Integer groupId) {
		super(groupId);
		setMessage(message);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Sets whether the event is cancelled.
	 *
	 * @param cancel true to cancel the event, false otherwise
	 */
	@Override
	public final void setCancelled(boolean cancel) {
		this.cancel = cancel;
		LOGGER.atFine().log("%s cancelled: %b", this.getClass().getSimpleName(), cancel);
	}

	/**
	 * Checks if the event is cancelled.
	 *
	 * @return true if the event is cancelled, false otherwise
	 */
	@Override
	public final boolean isCancelled() {
		return cancel;
	}

	/**
	 * Returns the message associated with this event.
	 *
	 * @return the message object
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Returns the user ID from the message.
	 *
	 * @return the user ID, or null if the message is null
	 */
	public Integer getUserId() {
		return message.getFromId();
	}

	/**
	 * Returns the peer ID from the message.
	 *
	 * @return the peer ID, or null if the message is null
	 */
	public Integer getPeer() {
		return message.getPeerId();
	}
	// endregion

	// region Private Methods
	/**
	 * Sets the message for this event.
	 *
	 * @param message the message to set
	 * @throws IllegalArgumentException if message is null
	 */
	private void setMessage(Message message) {
		this.message = Preconditions.checkNotNull(message, "Message cannot be null");
		LOGGER.atFine().log("Set message for %s", this.getClass().getSimpleName());
	}
	// endregion
}