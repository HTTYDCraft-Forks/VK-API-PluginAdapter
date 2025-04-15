package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.UserUnblock;

/**
 * Event triggered when a user is unblocked in a VK group.
 */
public class VKUserUnblockEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private UserUnblock unblock;
	// endregion

	// region Constructors
	/**
	 * Constructs a user unblock event with the specified unblock object and group ID.
	 *
	 * @param unblock the user unblock object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if unblock or groupId is null
	 */
	public VKUserUnblockEvent(UserUnblock unblock, Integer groupId) {
		super(groupId);
		setUnblock(unblock);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the user unblock object associated with this event.
	 *
	 * @return the user unblock object
	 */
	public UserUnblock getUnblock() {
		return unblock;
	}

	/**
	 * Sets the user unblock object for this event.
	 *
	 * @param unblock the user unblock object to set
	 * @throws IllegalArgumentException if unblock is null
	 */
	public void setUnblock(UserUnblock unblock) {
		this.unblock = Preconditions.checkNotNull(unblock, "Unblock cannot be null");
		LOGGER.atFine().log("Set unblock for %s", this.getClass().getSimpleName());
	}
	// endregion
}