package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.GroupOfficersEdit;

/**
 * Event triggered when group officers are edited in a VK group.
 */
public class VKGroupOfficersEditEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private GroupOfficersEdit officersEdit;
	// endregion

	// region Constructors
	/**
	 * Constructs a group officers edit event with the specified edit info and group ID.
	 *
	 * @param officersEdit the group officers edit object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if officersEdit or groupId is null
	 */
	public VKGroupOfficersEditEvent(GroupOfficersEdit officersEdit, Integer groupId) {
		super(groupId);
		setOfficersEdit(officersEdit);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the group officers edit information.
	 *
	 * @return the group officers edit object
	 */
	public GroupOfficersEdit getOfficersEdit() {
		return officersEdit;
	}

	/**
	 * Sets the group officers edit information.
	 *
	 * @param officersEdit the group officers edit object to set
	 * @throws IllegalArgumentException if officersEdit is null
	 */
	public void setOfficersEdit(GroupOfficersEdit officersEdit) {
		this.officersEdit = Preconditions.checkNotNull(officersEdit, "Officers edit cannot be null");
		LOGGER.atFine().log("Set officers edit for %s", this.getClass().getSimpleName());
	}
	// endregion
}