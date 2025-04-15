package com.httydcraft.vk.api.bukkit.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Event triggered when a new photo comment is created in a VK group.
 */
public class VKPhotoCommentEvent extends VKPhotoCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a photo comment event with the specified photo comment and group ID.
	 *
	 * @param photoComment the new photo comment
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}