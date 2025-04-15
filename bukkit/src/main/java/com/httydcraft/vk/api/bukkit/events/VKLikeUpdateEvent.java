package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.LikeAddRemove;

/**
 * Abstract base class for VK like-related update events.
 */
public abstract class VKLikeUpdateEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	protected LikeAddRemove likeInfo;
	// endregion

	// region Constructors
	/**
	 * Constructs a like update event with the specified like information and group ID.
	 *
	 * @param likeInfo the like add/remove object
	 * @param groupId  the ID of the VK group
	 * @throws IllegalArgumentException if likeInfo or groupId is null
	 */
	public VKLikeUpdateEvent(LikeAddRemove likeInfo, Integer groupId) {
		super(groupId);
		setLikeInfo(likeInfo);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the like information associated with this event.
	 *
	 * @return the like add/remove object
	 */
	public LikeAddRemove getLikeInfo() {
		return likeInfo;
	}

	/**
	 * Sets the like information for this event.
	 *
	 * @param likeInfo the like add/remove object to set
	 * @throws IllegalArgumentException if likeInfo is null
	 */
	public void setLikeInfo(LikeAddRemove likeInfo) {
		this.likeInfo = Preconditions.checkNotNull(likeInfo, "Like info cannot be null");
		LOGGER.atFine().log("Set like info for %s", this.getClass().getSimpleName());
	}
	// endregion
}