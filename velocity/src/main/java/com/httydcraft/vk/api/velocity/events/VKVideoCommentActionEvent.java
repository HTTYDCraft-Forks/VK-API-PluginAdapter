package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Abstract base class for VK video comment action events.
 */
public abstract class VKVideoCommentActionEvent extends AbstractVkEvent {
	// region Fields
	private VideoComment videoComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKVideoCommentActionEvent with the specified video comment and group ID.
	 *
	 * @param videoComment the video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentActionEvent(VideoComment videoComment, Integer groupId) {
		super(groupId);
		setVideoComment(videoComment);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the video comment associated with this event.
	 *
	 * @return the video comment object
	 */
	public VideoComment getVideoComment() {
		return videoComment;
	}

	/**
	 * Sets the video comment for this event.
	 *
	 * @param videoComment the video comment object
	 * @throws IllegalArgumentException if videoComment is null
	 */
	public void setVideoComment(VideoComment videoComment) {
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		this.videoComment = videoComment;
	}
	// endregion
}