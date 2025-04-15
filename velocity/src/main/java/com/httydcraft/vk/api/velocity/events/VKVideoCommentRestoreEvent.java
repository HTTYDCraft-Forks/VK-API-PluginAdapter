package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Event triggered when a video comment is restored in a VK group.
 */
public class VKVideoCommentRestoreEvent extends VKVideoCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKVideoCommentRestoreEvent with the specified video comment and group ID.
	 *
	 * @param videoComment the video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentRestoreEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
	}
	// endregion
}