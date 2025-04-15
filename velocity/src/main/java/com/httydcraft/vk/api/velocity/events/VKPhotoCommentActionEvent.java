package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Abstract base class for VK photo comment action events.
 */
public abstract class VKPhotoCommentActionEvent extends AbstractVkEvent {
	// region Fields
	protected PhotoComment photoComment;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKPhotoCommentActionEvent with the specified photo comment and group ID.
	 *
	 * @param photoComment the photo comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentActionEvent(PhotoComment photoComment, Integer groupId) {
		super(groupId);
		setPhotoComment(photoComment);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the photo comment associated with this event.
	 *
	 * @return the photo comment object
	 */
	public PhotoComment getPhotoComment() {
		return photoComment;
	}

	/**
	 * Sets the photo comment for this event.
	 *
	 * @param photoComment the photo comment object
	 * @throws IllegalArgumentException if photoComment is null
	 */
	public void setPhotoComment(PhotoComment photoComment) {
		Preconditions.checkNotNull(photoComment, "Photo comment cannot be null");
		this.photoComment = photoComment;
	}
	// endregion
}