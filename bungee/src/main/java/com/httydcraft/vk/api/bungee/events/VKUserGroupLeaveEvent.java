package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.GroupLeave;

/**
 * Event triggered when a user leaves a VK group.
 */
public class VKUserGroupLeaveEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private GroupLeave leave;
	// endregion

	// region Constructors
	/**
	 * Constructs a user group leave event with the specified leave info and group ID.
	 *
	 * @param leave   the group leave object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if leave or groupId is null
	 */
	public VKUserGroupLeaveEvent(GroupLeave leave, Integer groupId) {
		super(groupId);
		setLeave(leave);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the group leave information.
	 *
	 * @return the group leave object
	 */
	public GroupLeave getLeave() {
		return leave;
	}

	/**
	 * Sets the group leave information.
	 *
	 * @param leave the group leave object to set
	 * @throws IllegalArgumentException if leave is null
	 */
	public void setLeave(GroupLeave leave) {
		this.leave = Preconditions.checkNotNull(leave, "Leave cannot be null");
		LOGGER.atFine().log("Set leave for %s", this.getClass().getSimpleName());
	}
	// endregion
}