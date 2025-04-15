package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.GroupChangeSettings;

/**
 * Event triggered when group settings are changed in a VK group.
 */
public class VKGroupChangeSettingsEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private GroupChangeSettings settings;
	// endregion

	// region Constructors
	/**
	 * Constructs a group settings change event with the specified settings and group ID.
	 *
	 * @param settings the group settings change object
	 * @param groupId  the ID of the VK group
	 * @throws IllegalArgumentException if settings or groupId is null
	 */
	public VKGroupChangeSettingsEvent(GroupChangeSettings settings, Integer groupId) {
		super(groupId);
		setSettings(settings);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the group settings change information.
	 *
	 * @return the group settings change object
	 */
	public GroupChangeSettings getSettings() {
		return settings;
	}

	/**
	 * Sets the group settings change information.
	 *
	 * @param settings the group settings change object to set
	 * @throws IllegalArgumentException if settings is null
	 */
	public void setSettings(GroupChangeSettings settings) {
		this.settings = Preconditions.checkNotNull(settings, "Settings cannot be null");
		LOGGER.atFine().log("Set settings for %s", this.getClass().getSimpleName());
	}
	// endregion
}