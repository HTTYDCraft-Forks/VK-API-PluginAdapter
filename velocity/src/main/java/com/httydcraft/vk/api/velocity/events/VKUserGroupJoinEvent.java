package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.GroupJoin;

/**
 * Event triggered when a user joins a VK group.
 */
public class VKUserGroupJoinEvent extends AbstractVkEvent {
	// region Fields
	private GroupJoin join;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKUserGroupJoinEvent with the specified group join and group ID.
	 *
	 * @param join    the group join object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if join or groupId is null
	 */
	public VKUserGroupJoinEvent(GroupJoin join, Integer groupId) {
		super(groupId);
		setJoin(join);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the group join associated with this event.
	 *
	 * @return the group join object
	 */
	public GroupJoin getJoin() {
		return join;
	}

	/**
	 * Sets the group join for this event.
	 *
	 * @param join the group join object
	 * @throws IllegalArgumentException if join is null
	 */
	public void setJoin(GroupJoin join) {
		Preconditions.checkNotNull(join, "Join cannot be null");
		this.join = join;
	}
	// endregion
}