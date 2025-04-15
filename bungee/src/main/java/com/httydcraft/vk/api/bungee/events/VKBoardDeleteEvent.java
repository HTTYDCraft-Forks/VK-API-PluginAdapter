package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.BoardPostDelete;

/**
 * Event triggered when a board post is deleted in a VK group.
 */
public class VKBoardDeleteEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private BoardPostDelete boardDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a board delete event with the specified deletion info and group ID.
	 *
	 * @param boardDelete the board post deletion object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if boardDelete or groupId is null
	 */
	public VKBoardDeleteEvent(BoardPostDelete boardDelete, Integer groupId) {
		super(groupId);
		setBoardDelete(boardDelete);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the board post deletion information.
	 *
	 * @return the board post deletion object
	 */
	public BoardPostDelete getBoardDelete() {
		return boardDelete;
	}

	/**
	 * Sets the board post deletion information.
	 *
	 * @param boardDelete the board post deletion object to set
	 * @throws IllegalArgumentException if boardDelete is null
	 */
	public void setBoardDelete(BoardPostDelete boardDelete) {
		this.boardDelete = Preconditions.checkNotNull(boardDelete, "Board delete cannot be null");
		LOGGER.atFine().log("Set board delete for %s", this.getClass().getSimpleName());
	}
	// endregion
}