package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.MarketCommentDelete;

/**
 * Event triggered when a market comment is deleted in a VK group.
 */
public class VKMarketCommentDeleteEvent extends AbstractVkEvent {
	// region Fields
	private MarketCommentDelete comment;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKMarketCommentDeleteEvent with the specified comment deletion and group ID.
	 *
	 * @param comment the market comment deletion object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentDeleteEvent(MarketCommentDelete comment, Integer groupId) {
		super(groupId);
		setComment(comment);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the market comment deletion associated with this event.
	 *
	 * @return the market comment deletion object
	 */
	public MarketCommentDelete getComment() {
		return comment;
	}

	/**
	 * Sets the market comment deletion for this event.
	 *
	 * @param comment the market comment deletion object
	 * @throws IllegalArgumentException if comment is null
	 */
	public void setComment(MarketCommentDelete comment) {
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		this.comment = comment;
	}
	// endregion
}