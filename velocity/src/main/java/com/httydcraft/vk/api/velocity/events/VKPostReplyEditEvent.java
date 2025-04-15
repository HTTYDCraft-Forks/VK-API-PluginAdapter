package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.wall.WallComment;

/**
 * Event triggered when a wall post reply is edited in a VK group.
 */
public class VKPostReplyEditEvent extends VKPostReplyActionEvent {
	// region Constructors
	/**
	 * Constructs a VKPostReplyEditEvent with the specified wall comment and group ID.
	 *
	 * @param postComment the wall comment object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if postComment or groupId is null
	 */
	public VKPostReplyEditEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
	}
	// endregion
}