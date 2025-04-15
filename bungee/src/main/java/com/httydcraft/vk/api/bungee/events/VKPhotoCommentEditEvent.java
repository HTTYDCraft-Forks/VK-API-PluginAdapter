package com.httydcraft.vk.api.bungee.events;

import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Event triggered when a photo comment is edited in a VK group.
 */
public class VKPhotoCommentEditEvent extends VKPhotoCommentActionEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	// endregion

	// region Constructors
	/**
	 * Constructs a photo comment edit event with the specified comment and group ID.
	 *
	 * @param photoComment the edited photo comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentEditEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion
}