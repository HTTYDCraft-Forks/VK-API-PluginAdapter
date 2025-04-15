package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.wall.Wallpost;

/**
 * Event triggered when a wall post is reposted in a VK group.
 */
public class VKPostRepostEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private Wallpost post;
	// endregion

	// region Constructors
	/**
	 * Constructs a post repost event with the specified post and group ID.
	 *
	 * @param post    the reposted wall post object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if post or groupId is null
	 */
	public VKPostRepostEvent(Wallpost post, Integer groupId) {
		super(groupId);
		setPost(post);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the reposted wall post.
	 *
	 * @return the wall post object
	 */
	public Wallpost getPost() {
		return post;
	}

	/**
	 * Sets the reposted wall post.
	 *
	 * @param post the wall post object to set
	 * @throws IllegalArgumentException if post is null
	 */
	public void setPost(Wallpost post) {
		this.post = Preconditions.checkNotNull(post, "Post cannot be null");
		LOGGER.atFine().log("Set post for %s", this.getClass().getSimpleName());
	}
	// endregion
}