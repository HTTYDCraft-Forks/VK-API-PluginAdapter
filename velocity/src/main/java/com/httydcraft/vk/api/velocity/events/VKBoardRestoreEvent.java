package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.board.TopicComment;

/**
 * Event triggered when a board post is restored in a VK group.
 */
public class VKBoardRestoreEvent extends VKBoardActionEvent {
	// region Constructors
	/**
	 * Constructs a VKBoardRestoreEvent with the specified topic comment and group ID.
	 *
	 * @param topicComment the topic comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if topicComment or groupId is null
	 */
	public VKBoardRestoreEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}
	// endregion
}