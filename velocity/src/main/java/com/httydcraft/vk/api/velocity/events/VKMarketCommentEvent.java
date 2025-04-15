package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Event triggered when a new market comment is created in a VK group.
 */
public class VKMarketCommentEvent extends VKMarketCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKMarketCommentEvent with the specified comment and group ID.
	 *
	 * @param comment the market comment object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}
	// endregion
}