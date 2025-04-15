package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.UserBlock;

/**
 * Event triggered when a user is blocked in a VK group.
 */
public class VKUserBlockEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private UserBlock block;
	// endregion

	// region Constructors
	/**
	 * Constructs a user block event with the specified block object and group ID.
	 *
	 * @param block   the user block object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if block or groupId is null
	 */
	public VKUserBlockEvent(UserBlock block, Integer groupId) {
		super(groupId);
		setBlock(block);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the user block object associated with this event.
	 *
	 * @return the user block object
	 */
	public UserBlock getBlock() {
		return block;
	}

	/**
	 * Sets the user block object for this event.
	 *
	 * @param block the user block object to set
	 * @throws IllegalArgumentException if block is null
	 */
	public void setBlock(UserBlock block) {
		this.block = Preconditions.checkNotNull(block, "Block cannot be null");
		LOGGER.atFine().log("Set block for %s", this.getClass().getSimpleName());
	}
	// endregion
}