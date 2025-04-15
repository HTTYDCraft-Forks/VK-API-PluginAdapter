package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.GroupJoin;

/**
 * Event triggered when a user joins a VK group.
 */
public class VKUserGroupJoinEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private GroupJoin join;
	// endregion

	// region Constructors
	/**
	 * Constructs a user group join event with the specified join info and group ID.
	 *
	 * @param join    the group join object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if join or groupId is null
	 */
	public VKUserGroupJoinEvent(GroupJoin join, Integer groupId) {
		super(groupId);
		setJoin(join);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the group join information.
	 *
	 * @return the group join object
	 */
	public GroupJoin getJoin() {
		return join;
	}

	/**
	 * Sets the group join information.
	 *
	 * @param join the group join object to set
	 * @throws IllegalArgumentException if join is null
	 */
	public void setJoin(GroupJoin join) {
		this.join = Preconditions.checkNotNull(join, "Join cannot be null");
		LOGGER.atFine().log("Set join for %s", this.getClass().getSimpleName());
	}
	// endregion
}