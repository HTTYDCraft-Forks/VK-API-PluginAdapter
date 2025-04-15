package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.GroupLeave;

/**
 * Event triggered when a user leaves a VK group.
 */
public class VKUserGroupLeaveEvent extends AbstractVkEvent {
	// region Fields
	private GroupLeave leave;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKUserGroupLeaveEvent with the specified group leave and group ID.
	 *
	 * @param leave   the group leave object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if leave or groupId is null
	 */
	public VKUserGroupLeaveEvent(GroupLeave leave, Integer groupId) {
		super(groupId);
		setLeave(leave);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the group leave associated with this event.
	 *
	 * @return the group leave object
	 */
	public GroupLeave getLeave() {
		return leave;
	}

	/**
	 * Sets the group leave for this event.
	 *
	 * @param leave the group leave object
	 * @throws IllegalArgumentException if leave is null
	 */
	public void setLeave(GroupLeave leave) {
		Preconditions.checkNotNull(leave, "Leave cannot be null");
		this.leave = leave;
	}
	// endregion
}