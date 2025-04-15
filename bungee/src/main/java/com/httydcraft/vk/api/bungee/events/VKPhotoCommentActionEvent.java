package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Abstract base class for photo comment-related events in a VK group.
 */
public abstract class VKPhotoCommentActionEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	protected PhotoComment photoComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a photo comment action event with the specified comment and group ID.
	 *
	 * @param photoComment the photo comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentActionEvent(PhotoComment photoComment, Integer groupId) {
		super(groupId);
		setPhotoComment(photoComment);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the photo comment associated with this event.
	 *
	 * @return the photo comment object
	 */
	public PhotoComment getPhotoComment() {
		return photoComment;
	}

	/**
	 * Sets the photo comment for this event.
	 *
	 * @param photoComment the photo comment to set
	 * @throws IllegalArgumentException if photoComment is null
	 */
	public void setPhotoComment(PhotoComment photoComment) {
		this.photoComment = Preconditions.checkNotNull(photoComment, "Photo comment cannot be null");
		LOGGER.atFine().log("Set photo comment for %s", this.getClass().getSimpleName());
	}
	// endregion
}