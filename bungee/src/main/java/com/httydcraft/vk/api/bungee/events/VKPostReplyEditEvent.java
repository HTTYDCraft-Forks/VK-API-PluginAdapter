package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.wall.WallComment;

/**
 * Event triggered when a wall post reply is edited in a VK group.
 */
public class VKPostReplyEditEvent extends VKPostReplyActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a post reply edit event with the specified comment and group ID.
	 *
	 * @param postComment the edited wall comment object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if postComment or groupId is null
	 */
	public VKPostReplyEditEvent(WallComment postComment, Integer groupId) {
		super(postComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}