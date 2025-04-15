package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.wall.Wallpost;

/**
 * Event triggered when a wall post is reposted in a VK group.
 */
public class VKPostRepostEvent extends AbstractVkEvent {
	// region Fields
	private Wallpost post;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKPostRepostEvent with the specified wall post and group ID.
	 *
	 * @param post    the wall post object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if post or groupId is null
	 */
	public VKPostRepostEvent(Wallpost post, Integer groupId) {
		super(groupId);
		setPost(post);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the wall post associated with this event.
	 *
	 * @return the wall post object
	 */
	public Wallpost getPost() {
		return post;
	}

	/**
	 * Sets the wall post for this event.
	 *
	 * @param post the wall post object
	 * @throws IllegalArgumentException if post is null
	 */
	public void setPost(Wallpost post) {
		Preconditions.checkNotNull(post, "Post cannot be null");
		this.post = post;
	}
	// endregion
}