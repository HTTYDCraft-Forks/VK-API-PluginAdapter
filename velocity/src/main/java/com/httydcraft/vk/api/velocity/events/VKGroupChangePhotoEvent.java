package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.GroupChangePhoto;

/**
 * Event triggered when a VK group's photo is changed.
 */
public class VKGroupChangePhotoEvent extends AbstractVkEvent {
	// region Fields
	private GroupChangePhoto photoChange;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKGroupChangePhotoEvent with the specified photo change and group ID.
	 *
	 * @param photoChange the group photo change object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if photoChange or groupId is null
	 */
	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange, Integer groupId) {
		super(groupId);
		setPhotoChange(photoChange);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the group photo change associated with this event.
	 *
	 * @return the group photo change object
	 */
	public GroupChangePhoto getPhotoChange() {
		return photoChange;
	}

	/**
	 * Sets the group photo change for this event.
	 *
	 * @param photoChange the group photo change object
	 * @throws IllegalArgumentException if photoChange is null
	 */
	public void setPhotoChange(GroupChangePhoto photoChange) {
		Preconditions.checkNotNull(photoChange, "Photo change cannot be null");
		this.photoChange = photoChange;
	}
	// endregion
}