package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.WallCommentDelete;

/**
 * Event triggered when a post comment is deleted in a VK group.
 */
public class VKPostReplyDeleteEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private WallCommentDelete postCommentDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a post reply delete event with the specified post comment deletion and group ID.
	 *
	 * @param postCommentDelete the post comment deletion object
	 * @param groupId           the ID of the VK group
	 * @throws IllegalArgumentException if postCommentDelete or groupId is null
	 */
	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete, Integer groupId) {
		super(groupId);
		setPostCommentDelete(postCommentDelete);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the post comment deletion object associated with this event.
	 *
	 * @return the post comment deletion object
	 */
	public WallCommentDelete getPostCommentDelete() {
		return postCommentDelete;
	}

	/**
	 * Sets the post comment deletion object for this event.
	 *
	 * @param postCommentDelete the post comment deletion object to set
	 * @throws IllegalArgumentException if postCommentDelete is null
	 */
	public void setPostCommentDelete(WallCommentDelete postCommentDelete) {
		this.postCommentDelete = Preconditions.checkNotNull(postCommentDelete, "Post comment delete cannot be null");
		LOGGER.atFine().log("Set post comment delete for %s", this.getClass().getSimpleName());
	}
	// endregion
}