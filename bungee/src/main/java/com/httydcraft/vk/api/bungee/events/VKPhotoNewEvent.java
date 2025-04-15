package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.photos.Photo;

/**
 * Event triggered when a new photo is added in a VK group.
 */
public class VKPhotoNewEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private Photo photo;
	// endregion

	// region Constructors
	/**
	 * Constructs a photo new event with the specified photo and group ID.
	 *
	 * @param photo   the photo object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if photo or groupId is null
	 */
	public VKPhotoNewEvent(Photo photo, Integer groupId) {
		super(groupId);
		setPhoto(photo);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the photo associated with this event.
	 *
	 * @return the photo object
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo for this event.
	 *
	 * @param photo the photo object to set
	 * @throws IllegalArgumentException if photo is null
	 */
	public void setPhoto(Photo photo) {
		this.photo = Preconditions.checkNotNull(photo, "Photo cannot be null");
		LOGGER.atFine().log("Set photo for %s", this.getClass().getSimpleName());
	}
	// endregion
}