package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.UserUnblock;

/**
 * Event triggered when a user is unblocked in a VK group.
 */
public class VKUserUnblockEvent extends AbstractVkEvent {
	// region Fields
	private UserUnblock unblock;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKUserUnblockEvent with the specified user unblock and group ID.
	 *
	 * @param unblock the user unblock object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if unblock or groupId is null
	 */
	public VKUserUnblockEvent(UserUnblock unblock, Integer groupId) {
		super(groupId);
		setUnblock(unblock);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the user unblock associated with this event.
	 *
	 * @return the user unblock object
	 */
	public UserUnblock getUnblock() {
		return unblock;
	}

	/**
	 * Sets the user unblock for this event.
	 *
	 * @param unblock the user unblock object
	 * @throws IllegalArgumentException if unblock is null
	 */
	public void setUnblock(UserUnblock unblock) {
		Preconditions.checkNotNull(unblock, "Unblock cannot be null");
		this.unblock = unblock;
	}
	// endregion
}