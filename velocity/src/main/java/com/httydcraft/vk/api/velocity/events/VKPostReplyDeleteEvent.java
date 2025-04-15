package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.WallCommentDelete;

/**
 * Event triggered when a wall post reply is deleted in a VK group.
 */
public class VKPostReplyDeleteEvent extends AbstractVkEvent {
	// region Fields
	private WallCommentDelete postCommentDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKPostReplyDeleteEvent with the specified comment deletion and group ID.
	 *
	 * @param postCommentDelete the wall comment deletion object
	 * @param groupId           the ID of the VK group
	 * @throws IllegalArgumentException if postCommentDelete or groupId is null
	 */
	public VKPostReplyDeleteEvent(WallCommentDelete postCommentDelete, Integer groupId) {
		super(groupId);
		setPostCommentDelete(postCommentDelete);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the wall comment deletion associated with this event.
	 *
	 * @return the wall comment deletion object
	 */
	public WallCommentDelete getPostCommentDelete() {
		return postCommentDelete;
	}

	/**
	 * Sets the wall comment deletion for this event.
	 *
	 * @param postCommentDelete the wall comment deletion object
	 * @throws IllegalArgumentException if postCommentDelete is null
	 */
	public void setPostCommentDelete(WallCommentDelete postCommentDelete) {
		Preconditions.checkNotNull(postCommentDelete, "Post comment delete cannot be null");
		this.postCommentDelete = postCommentDelete;
	}
	// endregion
}