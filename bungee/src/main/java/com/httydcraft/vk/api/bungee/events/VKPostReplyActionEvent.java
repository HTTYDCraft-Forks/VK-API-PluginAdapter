package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.wall.WallComment;

/**
 * Abstract base class for wall post reply-related events in a VK group.
 */
public abstract class VKPostReplyActionEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private WallComment postComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a post reply action event with the specified comment and group ID.
	 *
	 * @param postComment the wall comment object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if postComment or groupId is null
	 */
	public VKPostReplyActionEvent(WallComment postComment, Integer groupId) {
		super(groupId);
		setPostComment(postComment);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the wall comment associated with this event.
	 *
	 * @return the wall comment object
	 */
	public WallComment getPostComment() {
		return postComment;
	}

	/**
	 * Sets the wall comment for this event.
	 *
	 * @param postComment the wall comment to set
	 * @throws IllegalArgumentException if postComment is null
	 */
	public void setPostComment(WallComment postComment) {
		this.postComment = Preconditions.checkNotNull(postComment, "Post comment cannot be null");
		LOGGER.atFine().log("Set post comment for %s", this.getClass().getSimpleName());
	}
	// endregion
}