package com.httydcraft.vk.api.velocity.events;

import com.vk.api.sdk.objects.callback.PhotoComment;

/**
 * Event triggered when a new photo comment is created in a VK group.
 */
public class VKPhotoCommentEvent extends VKPhotoCommentActionEvent {
	// region Constructors
	/**
	 * Constructs a VKPhotoCommentEvent with the specified photo comment and group ID.
	 *
	 * @param photoComment the photo comment object
	 * @param groupId      the ID of the VK group
	 * @throws IllegalArgumentException if photoComment or groupId is null
	 */
	public VKPhotoCommentEvent(PhotoComment photoComment, Integer groupId) {
		super(photoComment, groupId);
	}
	// endregion
}