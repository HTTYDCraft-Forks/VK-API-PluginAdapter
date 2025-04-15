package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.JsonObject;

/**
 * Event triggered when a JSON event is received from a VK group.
 */
public class VKJsonEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private JsonObject jsonObject;
	// endregion

	// region Constructors
	/**
	 * Constructs a JSON event with the specified JSON object and group ID.
	 *
	 * @param jsonObject the JSON object
	 * @param groupId    the ID of the VK group
	 * @throws IllegalArgumentException if jsonObject or groupId is null
	 */
	public VKJsonEvent(JsonObject jsonObject, Integer groupId) {
		super(groupId);
		setJsonObject(jsonObject);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the JSON object associated with this event.
	 *
	 * @return the JSON object
	 */
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * Returns the type of the JSON event.
	 *
	 * @return the event type
	 * @throws IllegalStateException if the type is not present in the JSON object
	 */
	public String getType() {
		Preconditions.checkState(jsonObject.has("type"), "JSON object missing 'type' field");
		return jsonObject.get("type").getAsString();
	}
	// endregion

	// region Private Methods
	/**
	 * Sets the JSON object for this event.
	 *
	 * @param jsonObject the JSON object to set
	 * @throws IllegalArgumentException if jsonObject is null
	 */
	private void setJsonObject(JsonObject jsonObject) {
		this.jsonObject = Preconditions.checkNotNull(jsonObject, "JSON object cannot be null");
		LOGGER.atFine().log("Set JSON object for %s", this.getClass().getSimpleName());
	}
	// endregion
}