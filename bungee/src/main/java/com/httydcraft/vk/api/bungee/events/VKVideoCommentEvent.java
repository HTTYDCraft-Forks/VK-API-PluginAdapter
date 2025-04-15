package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Event triggered when a new video comment is added in a VK group.
 */
public class VKVideoCommentEvent extends VKVideoCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a video comment event with the specified comment and group ID.
	 *
	 * @param videoComment the new video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}