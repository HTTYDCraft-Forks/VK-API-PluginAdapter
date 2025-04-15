package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MarketCommentDelete;

/**
 * Event triggered when a market comment is deleted in a VK group.
 */
public class VKMarketCommentDeleteEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private MarketCommentDelete comment;
	// endregion

	// region Constructors
	/**
	 * Constructs a market comment delete event with the specified deletion info and group ID.
	 *
	 * @param comment the market comment deletion object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentDeleteEvent(MarketCommentDelete comment, Integer groupId) {
		super(groupId);
		setComment(comment);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the market comment deletion information.
	 *
	 * @return the market comment deletion object
	 */
	public MarketCommentDelete getComment() {
		return comment;
	}

	/**
	 * Sets the market comment deletion information.
	 *
	 * @param comment the market comment deletion object to set
	 * @throws IllegalArgumentException if comment is null
	 */
	public void setComment(MarketCommentDelete comment) {
		this.comment = Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Set comment for %s", this.getClass().getSimpleName());
	}
	// endregion
}