package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Event triggered when a video comment is edited in a VK group.
 */
public class VKVideoCommentEditEvent extends VKVideoCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKVideoCommentEditEvent with the specified video comment and group ID.
	 *
	 * @param videoComment the video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentEditEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
	}
	// endregion
}