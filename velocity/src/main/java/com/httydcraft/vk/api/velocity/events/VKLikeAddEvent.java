package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.LikeAddRemove;

/**
 * Event triggered when a like is added in a VK group.
 */
public class VKLikeAddEvent extends VKLikeUpdateEvent {
	// region Constructors
	/**
	 * Constructs a VKLikeAddEvent with the specified like information and group ID.
	 *
	 * @param likeAdd the like add/remove object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if likeAdd or groupId is null
	 */
	public VKLikeAddEvent(LikeAddRemove likeAdd, Integer groupId) {
		super(likeAdd, groupId);
	}
	// endregion
}