package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.VideoCommentDelete;

/**
 * Event triggered when a video comment is deleted in a VK group.
 */
public class VKVideoCommentDeleteEvent extends AbstractVkEvent {
	// region Fields
	private VideoCommentDelete videoCommentDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKVideoCommentDeleteEvent with the specified comment deletion and group ID.
	 *
	 * @param videoCommentDelete the video comment deletion object
	 * @param groupId            the ID of the VK group
	 * @throws IllegalArgumentException if videoCommentDelete or groupId is null
	 */
	public VKVideoCommentDeleteEvent(VideoCommentDelete videoCommentDelete, Integer groupId) {
		super(groupId);
		setVideoCommentDelete(videoCommentDelete);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the video comment deletion associated with this event.
	 *
	 * @return the video comment deletion object
	 */
	public VideoCommentDelete getVideoCommentDelete() {
		return videoCommentDelete;
	}

	/**
	 * Sets the video comment deletion for this event.
	 *
	 * @param videoCommentDelete the video comment deletion object
	 * @throws IllegalArgumentException if videoCommentDelete is null
	 */
	public void setVideoCommentDelete(VideoCommentDelete videoCommentDelete) {
		Preconditions.checkNotNull(videoCommentDelete, "Video comment delete cannot be null");
		this.videoCommentDelete = videoCommentDelete;
	}
	// endregion
}