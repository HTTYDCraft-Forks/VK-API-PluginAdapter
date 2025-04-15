package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Abstract base class for market comment-related events in a VK group.
 */
public abstract class VKMarketCommentActionEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private MarketComment comment;
	// endregion

	// region Constructors
	/**
	 * Constructs a market comment action event with the specified comment and group ID.
	 *
	 * @param comment the market comment object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentActionEvent(MarketComment comment, Integer groupId) {
		super(groupId);
		setComment(comment);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the market comment associated with this event.
	 *
	 * @return the market comment object
	 */
	public MarketComment getComment() {
		return comment;
	}

	/**
	 * Sets the market comment for this event.
	 *
	 * @param comment the market comment to set
	 * @throws IllegalArgumentException if comment is null
	 */
	public void setComment(MarketComment comment) {
		this.comment = Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Set comment for %s", this.getClass().getSimpleName());
	}
	// endregion
}