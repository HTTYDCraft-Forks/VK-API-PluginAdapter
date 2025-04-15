package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.photos.Photo;

/**
 * Event triggered when a new photo is added to a VK group.
 */
public class VKPhotoNewEvent extends AbstractVkEvent {
	// region Fields
	private Photo photo;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKPhotoNewEvent with the specified photo and group ID.
	 *
	 * @param photo   the photo object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if photo or groupId is null
	 */
	public VKPhotoNewEvent(Photo photo, Integer groupId) {
		super(groupId);
		setPhoto(photo);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the photo associated with this event.
	 *
	 * @return the photo object
	 */
	public Photo getPhoto() {
		return photo;
	}

	/**
	 * Sets the photo for this event.
	 *
	 * @param photo the photo object
	 * @throws IllegalArgumentException if photo is null
	 */
	public void setPhoto(Photo photo) {
		Preconditions.checkNotNull(photo, "Photo cannot be null");
		this.photo = photo;
	}
	// endregion
}