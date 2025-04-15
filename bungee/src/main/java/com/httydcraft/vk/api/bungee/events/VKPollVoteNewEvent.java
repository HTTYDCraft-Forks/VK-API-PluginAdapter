package com.httydcraft.vk.api.bungee.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.vk.api.sdk.objects.callback.PollVoteNew;

/**
 * Event triggered when a new vote is cast in a poll in a VK group.
 */
public class VKPollVoteNewEvent extends AbstractVkEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private PollVoteNew pollVote;
	// endregion

	// region Constructors
	/**
	 * Constructs a poll vote new event with the specified vote info and group ID.
	 *
	 * @param pollVote the poll vote object
	 * @param groupId  the ID of the VK group
	 * @throws IllegalArgumentException if pollVote or groupId is null
	 */
	public VKPollVoteNewEvent(PollVoteNew pollVote, Integer groupId) {
		super(groupId);
		setPollVote(pollVote);
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the poll vote information.
	 *
	 * @return the poll vote object
	 */
	public PollVoteNew getPollVote() {
		return pollVote;
	}

	/**
	 * Sets the poll vote information.
	 *
	 * @param pollVote the poll vote object to set
	 * @throws IllegalArgumentException if pollVote is null
	 */
	public void setPollVote(PollVoteNew pollVote) {
		this.pollVote = Preconditions.checkNotNull(pollVote, "Poll vote cannot be null");
		LOGGER.atFine().log("Set poll vote for %s", this.getClass().getSimpleName());
	}
	// endregion
}