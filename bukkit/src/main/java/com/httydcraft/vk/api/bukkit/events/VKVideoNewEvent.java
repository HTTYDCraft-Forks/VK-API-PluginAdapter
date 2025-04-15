package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.video.Video;

/**
 * Event triggered when a new video is added to a VK group.
 */
public class VKVideoNewEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private Video video;
	// endregion

	// region Constructors
	/**
	 * Constructs a video new event with the specified video and group ID.
	 *
	 * @param video   the video object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if video or groupId is null
	 */
	public VKVideoNewEvent(Video video, Integer groupId) {
		super(groupId);
		setVideo(video);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the video object associated with this event.
	 *
	 * @return the video object
	 */
	public Video getVideo() {
		return video;
	}

	/**
	 * Sets the video object for this event.
	 *
	 * @param video the video object to set
	 * @throws IllegalArgumentException if video is null
	 */
	public void setVideo(Video video) {
		this.video = Preconditions.checkNotNull(video, "Video cannot be null");
		LOGGER.atFine().log("Set video for %s", this.getClass().getSimpleName());
	}
	// endregion
}