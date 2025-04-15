package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.audio.Audio;

/**
 * Event triggered when a new audio is added to a VK group.
 */
public class VKAudioNewEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private Audio audio;
	// endregion

	// region Constructors
	/**
	 * Constructs a new audio event for the specified group and audio object.
	 *
	 * @param audio   the audio object associated with the event
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if audio or groupId is null
	 */
	public VKAudioNewEvent(Audio audio, Integer groupId) {
		super(groupId);
		setAudio(audio);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the audio object associated with this event.
	 *
	 * @return the audio object
	 */
	public Audio getAudio() {
		return audio;
	}

	/**
	 * Sets the audio object for this event.
	 *
	 * @param audio the audio object to set
	 * @throws IllegalArgumentException if audio is null
	 */
	public void setAudio(Audio audio) {
		this.audio = Preconditions.checkNotNull(audio, "Audio cannot be null");
		LOGGER.atFine().log("Set audio for %s", this.getClass().getSimpleName());
	}
	// endregion
}