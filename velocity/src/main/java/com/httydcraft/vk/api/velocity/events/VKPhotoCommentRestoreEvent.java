package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Event triggered when a photo comment is restored in a VK group.
 */
public class VKPhotoCommentRestoreEvent extends VKPhotoCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKPhotoCommentRestoreEvent with the specified photo comment and group ID.
	 *
	 * @param photoComment the photo comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentRestoreEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}
	// endregion
}