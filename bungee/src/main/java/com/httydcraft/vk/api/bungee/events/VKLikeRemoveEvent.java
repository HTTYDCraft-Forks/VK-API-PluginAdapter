package com.httydcraft.vk.api.bungee.events;

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
	 * Constructs a like remove event with the specified like info and group ID.
	 *
	 * @param likeRemove the like remove object
	 * @param groupId    the ID of the VK group
	 * @throws IllegalArgumentException if likeRemove or groupId is null
	 */
	public VKLikeRemoveEvent(LikeAddRemove likeRemove, Integer groupId) {
		super(likeRemove, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}