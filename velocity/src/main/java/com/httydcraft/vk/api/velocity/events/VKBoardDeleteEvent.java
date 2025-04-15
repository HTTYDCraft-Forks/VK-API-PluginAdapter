package com.httydcraft.vk.api.velocity.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.BoardPostDelete;

/**
 * Event triggered when a board post is deleted in a VK group.
 */
public class VKBoardDeleteEvent extends AbstractVkEvent {
	// region Fields
	private BoardPostDelete boardDelete;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKBoardDeleteEvent with the specified board post deletion and group ID.
	 *
	 * @param boardDelete the board post deletion object
	 * @param groupId     the ID of the VK group
	 * @throws IllegalArgumentException if boardDelete or groupId is null
	 */
	public VKBoardDeleteEvent(BoardPostDelete boardDelete, Integer groupId) {
		super(groupId);
		setBoardDelete(boardDelete);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the board post deletion associated with this event.
	 *
	 * @return the board post deletion object
	 */
	public BoardPostDelete getBoardDelete() {
		return boardDelete;
	}

	/**
	 * Sets the board post deletion for this event.
	 *
	 * @param boardDelete the board post deletion object
	 * @throws IllegalArgumentException if boardDelete is null
	 */
	public void setBoardDelete(BoardPostDelete boardDelete) {
		Preconditions.checkNotNull(boardDelete, "Board delete cannot be null");
		this.boardDelete = boardDelete;
	}
	// endregion
}