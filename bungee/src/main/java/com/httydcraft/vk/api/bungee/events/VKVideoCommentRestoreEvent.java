package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.VideoComment;

/**
 * Event triggered when a video comment is restored in a VK group.
 */
public class VKVideoCommentRestoreEvent extends VKVideoCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a video comment restore event with the specified comment and group ID.
	 *
	 * @param videoComment the restored video comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if videoComment or groupId is null
	 */
	public VKVideoCommentRestoreEvent(VideoComment videoComment, Integer groupId) {
		super(videoComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}