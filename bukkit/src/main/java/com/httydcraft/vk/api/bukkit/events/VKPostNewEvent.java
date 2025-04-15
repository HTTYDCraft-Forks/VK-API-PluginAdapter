package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.wall.Wallpost;

/**
 * Event triggered when a new post is created in a VK group.
 */
public class VKPostNewEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private Wallpost post;
	// endregion

	// region Constructors
	/**
	 * Constructs a post new event with the specified post and group ID.
	 *
	 * @param post    the post object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if post or groupId is null
	 */
	public VKPostNewEvent(Wallpost post, Integer groupId) {
		super(groupId);
		setPost(post);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the post object associated with this event.
	 *
	 * @return the post object
	 */
	public Wallpost getPost() {
		return post;
	}

	/**
	 * Sets the post object for this event.
	 *
	 * @param post the post object to set
	 * @throws IllegalArgumentException if post is null
	 */
	public void setPost(Wallpost post) {
		this.post = Preconditions.checkNotNull(post, "Post cannot be null");
		LOGGER.atFine().log("Set post for %s", this.getClass().getSimpleName());
	}
	// endregion
}