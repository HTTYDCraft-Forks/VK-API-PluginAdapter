package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.vk.api.sdk.objects.callback.PollVoteNew;

/**
 * Event triggered when a new poll vote is cast in a VK group.
 */
public class VKPollVoteNewEvent extends AbstractVkEvent {
	// region Fields
	private PollVoteNew pollVote;
	// endregion

	// region Constructors
	/**
	 * Constructs a VKPollVoteNewEvent with the specified poll vote and group ID.
	 *
	 * @param pollVote the poll vote object
	 * @param groupId  the ID of the VK group
	 * @throws IllegalArgumentException if pollVote or groupId is null
	 */
	public VKPollVoteNewEvent(PollVoteNew pollVote, Integer groupId) {
		super(groupId);
		setPollVote(pollVote);
	}
	// endregion

	// region Public Methods
	/**
	 * Retrieves the poll vote associated with this event.
	 *
	 * @return the poll vote object
	 */
	public PollVoteNew getPollVote() {
		return pollVote;
	}

	/**
	 * Sets the poll vote for this event.
	 *
	 * @param pollVote the poll vote object
	 * @throws IllegalArgumentException if pollVote is null
	 */
	public void setPollVote(PollVoteNew pollVote) {
		Preconditions.checkNotNull(pollVote, "Poll vote cannot be null");
		this.pollVote = pollVote;
	}
	// endregion
}