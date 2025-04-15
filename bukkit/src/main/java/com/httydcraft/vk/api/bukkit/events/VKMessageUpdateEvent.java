package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.messages.Message;
import org.bukkit.event.Cancellable;

/**
 * Abstract base class for VK message-related update events, implementing {@link Cancellable}.
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
	 * Sets whether this event is cancelled.
	 *
	 * @param cancel true to cancel the event, false otherwise
	 */
	@Override
	public final void setCancelled(boolean cancel) {
		this.cancel = cancel;
		LOGGER.atFine().log("Set cancelled state to %b for %s", cancel, this.getClass().getSimpleName());
	}

	/**
	 * Returns whether this event is cancelled.
	 *
	 * @return true if the event is cancelled, false otherwise
	 */
	@Override
	public final boolean isCancelled() {
		return cancel;
	}

	/**
	 * Returns the message object associated with this event.
	 *
	 * @return the message object
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Returns the user ID associated with the message.
	 *
	 * @return the user ID
	 */
	public Integer getUserId() {
		return message.getFromId();
	}

	/**
	 * Returns the peer ID associated with the message.
	 *
	 * @return the peer ID
	 */
	public Integer getPeer() {
		return message.getPeerId();
	}

	/**
	 * Sets the message object for this event.
	 *
	 * @param message the message object to set
	 * @throws IllegalArgumentException if message is null
	 */
	private void setMessage(Message message) {
		this.message = Preconditions.checkNotNull(message, "Message cannot be null");
		LOGGER.atFine().log("Set message for %s", this.getClass().getSimpleName());
	}
	// endregion
}