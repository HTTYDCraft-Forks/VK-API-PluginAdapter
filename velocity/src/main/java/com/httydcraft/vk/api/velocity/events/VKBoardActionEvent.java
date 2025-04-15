package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.board.TopicComment;

/**
 * Abstract base class for VK board action events.
 */
public abstract class VKBoardActionEvent extends AbstractVkEvent {
	// region Fields
	protected TopicComment topicComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKBoardActionEvent with the specified topic comment and group ID.
	 *
	 * @param topicComment the topic comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if topicComment or groupId is null
	 */
	public VKBoardActionEvent(TopicComment topicComment, Integer groupId) {
		super(groupId);
		setTopicComment(topicComment);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the topic comment associated with this event.
	 *
	 * @return the topic comment object
	 */
	public TopicComment getTopicComment() {
		return topicComment;
	}

	/**
	 * Sets the topic comment for this event.
	 *
	 * @param topicComment the topic comment object
	 * @throws IllegalArgumentException if topicComment is null
	 */
	public void setTopicComment(TopicComment topicComment) {
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		this.topicComment = topicComment;
	}
	// endregion
}