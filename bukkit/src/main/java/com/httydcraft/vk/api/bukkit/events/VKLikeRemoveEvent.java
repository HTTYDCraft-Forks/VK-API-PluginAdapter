package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.LikeAddRemove;

/**
 * Event triggered when a like is removed in a VK group.
 */
public class VKLikeRemoveEvent extends VKLikeUpdateEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a like remove event with the specified like information and group ID.
	 *
	 * @param likeAdd the like add/remove object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if likeAdd or groupId is null
	 */
	public VKLikeRemoveEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}