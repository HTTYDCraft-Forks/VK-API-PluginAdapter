package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.board.TopicComment;

/**
 * Event triggered when a board topic comment is edited in a VK group.
 */
public class VKBoardEditEvent extends VKBoardActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a board edit event with the specified topic comment and group ID.
	 *
	 * @param topicComment the edited topic comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if topicComment or groupId is null
	 */
	public VKBoardEditEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}