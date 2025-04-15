package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Event triggered when a new market comment is created in a VK group.
 */
public class VKMarketCommentEvent extends VKMarketCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a market comment event with the specified comment and group ID.
	 *
	 * @param comment the new market comment
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}