package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.wall.WallComment;

/**
 * Event triggered when a post comment is restored in a VK group.
 */
public class VKPostReplyRestoreEvent extends VKPostReplyActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a post reply restore event with the specified post comment and group ID.
	 *
	 * @param postComment the restored post comment
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if postComment or groupId is null
	 */
	public VKPostReplyRestoreEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}