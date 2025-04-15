package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.GroupChangePhoto;

/**
 * Event triggered when the group photo is changed in a VK group.
 */
public class VKGroupChangePhotoEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private GroupChangePhoto photoChange;
	// endregion

	// region Constructors
	/**
	 * Constructs a group photo change event with the specified photo change and group ID.
	 *
	 * @param photoChange the group photo change object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if photoChange or groupId is null
	 */
	public VKGroupChangePhotoEvent(GroupChangePhoto photoChange, Integer groupId) {
		super(groupId);
		setPhotoChange(photoChange);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the group photo change object associated with this event.
	 *
	 * @return the group photo change object
	 */
	public GroupChangePhoto getPhotoChange() {
		return photoChange;
	}

	/**
	 * Sets the group photo change object for this event.
	 *
	 * @param photoChange the group photo change object to set
	 * @throws IllegalArgumentException if photoChange is null
	 */
	public void setPhotoChange(GroupChangePhoto photoChange) {
		this.photoChange = Preconditions.checkNotNull(photoChange, "Photo change cannot be null");
		LOGGER.atFine().log("Set photo change for %s", this.getClass().getSimpleName());
	}
	// endregion
}