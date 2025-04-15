package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.board.TopicComment;

/**
 * Event triggered when a new board topic comment is added in a VK group.
 */
public class VKBoardNewEvent extends VKBoardActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a board new event with the specified topic comment and group ID.
	 *
	 * @param topicComment the new topic comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if topicComment or groupId is null
	 */
	public VKBoardNewEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}