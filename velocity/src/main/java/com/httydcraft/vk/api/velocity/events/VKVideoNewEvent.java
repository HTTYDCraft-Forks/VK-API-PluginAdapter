package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.video.Video;

/**
 * Event triggered when a new video is added to a VK group.
 */
public class VKVideoNewEvent extends AbstractVkEvent {
	// region Fields
	private Video video;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKVideoNewEvent with the specified video and group ID.
	 *
	 * @param video   the video object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if video or groupId is null
	 */
	public VKVideoNewEvent(Video video, Integer groupId) {
		super(groupId);
		setVideo(video);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the video associated with this event.
	 *
	 * @return the video object
	 */
	public Video getVideo() {
		return video;
	}

	/**
	 * Sets the video for this event.
	 *
	 * @param video the video object
	 * @throws IllegalArgumentException if video is null
	 */
	public void setVideo(Video video) {
		Preconditions.checkNotNull(video, "Video cannot be null");
		this.video = video;
	}
	// endregion
}