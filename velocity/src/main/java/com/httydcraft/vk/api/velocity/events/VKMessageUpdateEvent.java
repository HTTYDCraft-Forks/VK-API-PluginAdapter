package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.ResultedEvent.GenericResult;
import com.vk.api.sdk.objects.messages.Message;

/**
 * Abstract base class for VK message update events (new, reply, edit).
 */
public abstract class VKMessageUpdateEvent extends AbstractVkEvent implements ResultedEvent<GenericResult> {
	// region Fields
	private GenericResult result = GenericResult.allowed();
	private Message message;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMessageUpdateEvent with the specified message and group ID.
	 *
	 * @param message the message object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if message or groupId is null
	 */
	public VKMessageUpdateEvent(Message message, Integer groupId) {
		super(groupId);
		setMessage(message);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the result of the event.
	 *
	 * @return the generic result
	 */
	@Override
	public GenericResult getResult() {
		return result;
	}

	/**
	 * Sets the result of the event.
	 *
	 * @param result the generic result
	 * @throws IllegalArgumentException if result is null
	 */
	@Override
	public void setResult(GenericResult result) {
		Preconditions.checkNotNull(result, "Result cannot be null");
		this.result = result;
	}

	/**
	 * Retrieves the message associated with this event.
	 *
	 * @return the message object
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Retrieves the user ID from the message.
	 *
	 * @return the user ID
	 */
	public Integer getUserId() {
		return message.getFromId();
	}

	/**
	 * Retrieves the peer ID from the message.
	 *
	 * @return the peer ID
	 */
	public Integer getPeer() {
		return message.getPeerId();
	}

	/**
	 * Sets the message for this event.
	 *
	 * @param message the message object
	 * @throws IllegalArgumentException if message is null
	 */
	private void setMessage(Message message) {
		Preconditions.checkNotNull(message, "Message cannot be null");
		this.message = message;
	}
	// endregion
}