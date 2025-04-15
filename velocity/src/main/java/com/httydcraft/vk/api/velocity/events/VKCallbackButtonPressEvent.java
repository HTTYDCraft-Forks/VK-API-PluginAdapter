package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.httydcraft.vk.api.core.api.parsers.objects.CallbackButtonEvent;

/**
 * Event triggered when a callback button is pressed in a VK group.
 */
public class VKCallbackButtonPressEvent extends AbstractVkEvent {
	// region Fields
	private CallbackButtonEvent buttonEvent;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKCallbackButtonPressEvent with the specified button event and group ID.
	 *
	 * @param buttonEvent the callback button event object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if buttonEvent or groupId is null
	 */
	public VKCallbackButtonPressEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		super(groupId);
		Preconditions.checkNotNull(buttonEvent, "Button event cannot be null");
		this.buttonEvent = buttonEvent;
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the callback button event associated with this event.
	 *
	 * @return the callback button event object
	 */
	public CallbackButtonEvent getButtonEvent() {
		return buttonEvent;
	}

	/**
	 * Sets the callback button event for this event.
	 *
	 * @param buttonEvent the callback button event object
	 * @param groupId     the ID of the VK group (unused, for compatibility)
	 * @throws IllegalArgumentException if buttonEvent is null
	 */
	public void setButtonEvent(CallbackButtonEvent buttonEvent, Integer groupId) {
		Preconditions.checkNotNull(buttonEvent, "Button event cannot be null");
		this.buttonEvent = buttonEvent;
	}
	// endregion
}