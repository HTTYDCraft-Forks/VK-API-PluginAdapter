package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Abstract base class for VK market comment action events.
 */
public abstract class VKMarketCommentActionEvent extends AbstractVkEvent {
	// region Fields
	private MarketComment comment;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMarketCommentActionEvent with the specified comment and group ID.
	 *
	 * @param comment the market comment object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentActionEvent(MarketComment comment, Integer groupId) {
		super(groupId);
		setComment(comment);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the market comment associated with this event.
	 *
	 * @return the market comment object
	 */
	public MarketComment getComment() {
		return comment;
	}

	/**
	 * Sets the market comment for this event.
	 *
	 * @param comment the market comment object
	 * @throws IllegalArgumentException if comment is null
	 */
	public void setComment(MarketComment comment) {
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		this.comment = comment;
	}
	// endregion
}