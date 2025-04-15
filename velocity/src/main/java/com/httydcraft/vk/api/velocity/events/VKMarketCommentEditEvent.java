package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.MarketComment;

/**
 * Event triggered when a market comment is edited in a VK group.
 */
public class VKMarketCommentEditEvent extends VKMarketCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKMarketCommentEditEvent with the specified comment and group ID.
	 *
	 * @param comment the market comment object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if comment or groupId is null
	 */
	public VKMarketCommentEditEvent(MarketComment comment, Integer groupId) {
		super(comment, groupId);
	}
	// endregion
}