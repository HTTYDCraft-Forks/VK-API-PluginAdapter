package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.board.TopicComment;

/**
 * Event triggered when a board post is edited in a VK group.
 */
public class VKBoardEditEvent extends VKBoardActionEvent {
	// region Constructors
	/**
	 * Constructs a VKBoardEditEvent with the specified topic comment and group ID.
	 *
	 * @param topicComment the topic comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if topicComment or groupId is null
	 */
	public VKBoardEditEvent(TopicComment topicComment, Integer groupId) {
		super(topicComment, groupId);
	}
	// endregion
}