package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.audio.Audio;

/**
 * Event triggered when a new audio is added to a VK group.
 */
public class VKAudioNewEvent extends AbstractVkEvent {
	// region Fields
	private Audio audio;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKAudioNewEvent with the specified audio and group ID.
	 *
	 * @param audio   the audio object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if audio or groupId is null
	 */
	public VKAudioNewEvent(Audio audio, Integer groupId) {
		super(groupId);
		setAudio(audio);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the audio associated with this event.
	 *
	 * @return the audio object
	 */
	public Audio getAudio() {
		return audio;
	}

	/**
	 * Sets the audio for this event.
	 *
	 * @param audio the audio object
	 * @throws IllegalArgumentException if audio is null
	 */
	public void setAudio(Audio audio) {
		Preconditions.checkNotNull(audio, "Audio cannot be null");
		this.audio = audio;
	}
	// endregion
}