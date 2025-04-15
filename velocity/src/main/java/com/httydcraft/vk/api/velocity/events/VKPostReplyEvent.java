package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.wall.WallComment;

/**
 * Event triggered when a new wall post reply is created in a VK group.
 */
public class VKPostReplyEvent extends VKPostReplyActionEvent {
	// region Constructors
	/**
	 * Constructs a VKPostReplyEvent with the specified wall comment and group ID.
	 *
	 * @param postComment the wall comment object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if postComment or groupId is null
	 */
	public VKPostReplyEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}
	// endregion
}