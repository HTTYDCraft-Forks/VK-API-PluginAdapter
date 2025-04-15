package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Event triggered when a market comment is edited in a VK group.
 */
public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a market comment edit event with the specified comment and group ID.
	 *
	 * @param comment the edited market comment
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentEditEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}