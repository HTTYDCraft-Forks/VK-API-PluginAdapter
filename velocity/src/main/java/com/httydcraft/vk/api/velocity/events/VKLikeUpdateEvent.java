package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.LikeAddRemove;

/**
 * Abstract base class for VK like update events (add or remove).
 */
public abstract class VKLikeUpdateEvent extends AbstractVkEvent {
	// region Fields
	protected LikeAddRemove likeInfo;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKLikeUpdateEvent with the specified like information and group ID.
	 *
	 * @param likeInfo the like add/remove object
	 * @param groupId  the ID of the VK group
	 * @throws IllegalArgumentException if likeInfo or groupId is null
	 */
	public VKLikeUpdateEvent(LikeAddRemove likeInfo, Integer groupId) {
		super(groupId);
		setLikeInfo(likeInfo);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the like information associated with this event.
	 *
	 * @return the like add/remove object
	 */
	public LikeAddRemove getLikeInfo() {
		return likeInfo;
	}

	/**
	 * Sets the like information for this event.
	 *
	 * @param likeInfo the like add/remove object
	 * @throws IllegalArgumentException if likeInfo is null
	 */
	public void setLikeInfo(LikeAddRemove likeInfo) {
		Preconditions.checkNotNull(likeInfo, "Like info cannot be null");
		this.likeInfo = likeInfo;
	}
	// endregion
}