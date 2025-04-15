package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Event triggered when a new video comment is created in a VK group.
 */
public class VKVideoCommentEvent extends VKVideoCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKVideoCommentEvent with the specified video comment and group ID.
	 *
	 * @param videoComment the video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
	}
	// endregion
}