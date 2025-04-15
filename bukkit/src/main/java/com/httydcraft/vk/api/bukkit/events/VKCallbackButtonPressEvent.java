package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.parsers.objects.CallbackButtonEvent;

/**
 * Event triggered when a callback button is pressed in a VK group.
 */
public class VKCallbackButtonPressEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private CallbackButtonEvent buttonEvent;
	// endregion

	// region Constructors
	/**
	 * Constructs a callback button press event with the specified button event and group ID.
	 *
	 * @param buttonEvent the callback button event object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if buttonEvent or groupId is null
	 */
	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		super(groupId);
		setButtonEvent(buttonEvent);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the callback button event object associated with this event.
	 *
	 * @return the callback button event object
	 */
	public CallbackButtonEvent getButtonEvent() {
		return buttonEvent;
	}

	/**
	 * Sets the callback button event object for this event.
	 *
	 * @param buttonEvent the callback button event object to set
	 * @throws IllegalArgumentException if buttonEvent is null
	 */
	public void setButtonEvent(CallbackButtonEvent buttonEvent) {
		this.buttonEvent = Preconditions.checkNotNull(buttonEvent, "Button event cannot be null");
		LOGGER.atFine().log("Set button event for %s", this.getClass().getSimpleName());
	}
	// endregion
}