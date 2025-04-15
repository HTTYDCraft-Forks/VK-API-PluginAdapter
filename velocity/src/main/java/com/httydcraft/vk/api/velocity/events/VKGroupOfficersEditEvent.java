package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.GroupOfficersEdit;

/**
 * Event triggered when a VK group's officers are edited.
 */
public class VKGroupOfficersEditEvent extends AbstractVkEvent {
	// region Fields
	private GroupOfficersEdit officersEdit;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKGroupOfficersEditEvent with the specified officers edit and group ID.
	 *
	 * @param officersEdit the group officers edit object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if officersEdit or groupId is null
	 */
	public VKGroupOfficersEditEvent(GroupOfficersEdit officersEdit, Integer groupId) {
		super(groupId);
		setOfficersEdit(officersEdit);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the group officers edit associated with this event.
	 *
	 * @return the group officers edit object
	 */
	public GroupOfficersEdit getOfficersEdit() {
		return officersEdit;
	}

	/**
	 * Sets the group officers edit for this event.
	 *
	 * @param officersEdit the group officers edit object
	 * @throws IllegalArgumentException if officersEdit is null
	 */
	public void setOfficersEdit(GroupOfficersEdit officersEdit) {
		Preconditions.checkNotNull(officersEdit, "Officers edit cannot be null");
		this.officersEdit = officersEdit;
	}
	// endregion
}