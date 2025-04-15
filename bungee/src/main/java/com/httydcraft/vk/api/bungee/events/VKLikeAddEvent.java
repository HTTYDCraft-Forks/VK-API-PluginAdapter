package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.LikeAddRemove;

/**
 * Event triggered when a like is added in a VK group.
 */
public class VKLikeAddEvent extends VKLikeUpdateEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a like add event with the specified like info and group ID.
	 *
	 * @param likeAdd the like add object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if likeAdd or groupId is null
	 */
	public VKLikeAddEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}