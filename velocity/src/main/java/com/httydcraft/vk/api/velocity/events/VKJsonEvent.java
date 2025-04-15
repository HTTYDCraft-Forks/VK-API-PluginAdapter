package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;

/**
 * Event triggered for a generic JSON event in a VK group.
 */
public class VKJsonEvent extends AbstractVkEvent {
	// region Fields
	private JsonObject jsonObject;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKJsonEvent with the specified JSON object and group ID.
	 *
	 * @param jsonObject the JSON object
	 * @param groupId    the ID of the VK group
	 * @throws IllegalArgumentException if jsonObject or groupId is null
	 */
	public VKJsonEvent(JsonObject jsonObject, Integer groupId) {
		super(groupId);
		setJsonObject(jsonObject);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the JSON object associated with this event.
	 *
	 * @return the JSON object
	 */
	public JsonObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * Retrieves the type of the JSON event.
	 *
	 * @return the event type as a string
	 * @throws IllegalStateException if the type field is missing or not a string
	 */
	public String getType() {
		Preconditions.checkState(jsonObject.has("type"), "JSON object missing 'type' field");
		return jsonObject.get("type").getAsString();
	}

	/**
	 * Sets the JSON object for this event.
	 *
	 * @param jsonObject the JSON object
	 * @throws IllegalArgumentException if jsonObject is null
	 */
	private void setJsonObject(JsonObject jsonObject) {
		Preconditions.checkNotNull(jsonObject, "JSON object cannot be null");
		this.jsonObject = jsonObject;
	}
	// endregion
}