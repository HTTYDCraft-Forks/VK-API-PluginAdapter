package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.PhotoCommentDelete;

/**
 * Event triggered when a photo comment is deleted in a VK group.
 */
public class VKPhotoCommentDeleteEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private PhotoCommentDelete photoCommentDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a photo comment delete event with the specified deletion info and group ID.
	 *
	 * @param photoCommentDelete the photo comment deletion object
	 * @param groupId            the ID of the VK group
	 * @throws IllegalArgumentException if photoCommentDelete or groupId is null
	 */
	public VKPhotoCommentDeleteEvent(PhotoCommentDelete photoCommentDelete, Integer groupId) {
		super(groupId);
		setPhotoCommentDelete(photoCommentDelete);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the photo comment deletion information.
	 *
	 * @return the photo comment deletion object
	 */
	public PhotoCommentDelete getPhotoCommentDelete() {
		return photoCommentDelete;
	}

	/**
	 * Sets the photo comment deletion information.
	 *
	 * @param photoCommentDelete the photo comment deletion object to set
	 * @throws IllegalArgumentException if photoCommentDelete is null
	 */
	public void setPhotoCommentDelete(PhotoCommentDelete photoCommentDelete) {
		this.photoCommentDelete = Preconditions.checkNotNull(photoCommentDelete, "Photo comment delete cannot be null");
		LOGGER.atFine().log("Set photo comment delete for %s", this.getClass().getSimpleName());
	}
	// endregion
}