package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Abstract base class for video comment-related events in a VK group.
 */
public abstract class VKVideoCommentActionEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	protected VideoComment videoComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a video comment action event with the specified comment and group ID.
	 *
	 * @param videoComment the video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentActionEvent(VideoComment videoComment, Integer groupId) {
		super(groupId);
		setVideoComment(videoComment);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the video comment associated with this event.
	 *
	 * @return the video comment object
	 */
	public VideoComment getVideoComment() {
		return videoComment;
	}

	/**
	 * Sets the video comment for this event.
	 *
	 * @param videoComment the video comment to set
	 * @throws IllegalArgumentException if videoComment is null
	 */
	public void setVideoComment(VideoComment videoComment) {
		this.videoComment = Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		LOGGER.atFine().log("Set video comment for %s", this.getClass().getSimpleName());
	}
	// endregion
}