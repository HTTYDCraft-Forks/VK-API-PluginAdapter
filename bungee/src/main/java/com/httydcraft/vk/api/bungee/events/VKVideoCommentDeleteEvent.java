package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.VideoCommentDelete;

/**
 * Event triggered when a video comment is deleted in a VK group.
 */
public class VKVideoCommentDeleteEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private VideoCommentDelete videoCommentDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a video comment delete event with the specified deletion info and group ID.
	 *
	 * @param videoCommentDelete the video comment deletion object
	 * @param groupId            the ID of the VK group
	 * @throws IllegalArgumentException if videoCommentDelete or groupId is null
	 */
	public VKVideoCommentDeleteEvent(VideoCommentDelete videoCommentDelete, Integer groupId) {
		super(groupId);
		setVideoCommentDelete(videoCommentDelete);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the video comment deletion information.
	 *
	 * @return the video comment deletion object
	 */
	public VideoCommentDelete getVideoCommentDelete() {
		return videoCommentDelete;
	}

	/**
	 * Sets the video comment deletion information.
	 *
	 * @param videoCommentDelete the video comment deletion object to set
	 * @throws IllegalArgumentException if videoCommentDelete is null
	 */
	public void setVideoCommentDelete(VideoCommentDelete videoCommentDelete) {
		this.videoCommentDelete = Preconditions.checkNotNull(videoCommentDelete, "Video comment delete cannot be null");
		LOGGER.atFine().log("Set video comment delete for %s", this.getClass().getSimpleName());
	}
	// endregion
}