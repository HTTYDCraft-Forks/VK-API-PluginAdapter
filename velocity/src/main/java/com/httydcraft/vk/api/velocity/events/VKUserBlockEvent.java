package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.UserBlock;

/**
 * Event triggered when a user is blocked in a VK group.
 */
public class VKUserBlockEvent extends AbstractVkEvent {
	// region Fields
	private UserBlock block;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKUserBlockEvent with the specified user block and group ID.
	 *
	 * @param block   the user block object
	 * @param groupId the ID of the VK group
	 * @throws IllegalArgumentException if block or groupId is null
	 */
	public VKUserBlockEvent(UserBlock block, Integer groupId) {
		super(groupId);
		setBlock(block);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the user block associated with this event.
	 *
	 * @return the user block object
	 */
	public UserBlock getBlock() {
		return block;
	}

	/**
	 * Sets the user block for this event.
	 *
	 * @param block the user block object
	 * @throws IllegalArgumentException if block is null
	 */
	public void setBlock(UserBlock block) {
		Preconditions.checkNotNull(block, "Block cannot be null");
		this.block = block;
	}
	// endregion
}