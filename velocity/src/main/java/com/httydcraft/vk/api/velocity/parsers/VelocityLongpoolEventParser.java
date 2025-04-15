package com.httydcraft.vk.api.velocity.parsers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.JsonObject;
import com.httydcraft.vk.api.core.api.parsers.AbstractLongpoolEventParser;
import com.httydcraft.vk.api.core.api.parsers.objects.CallbackButtonEvent;
import com.httydcraft.vk.api.core.api.parsers.objects.MessageTyping;
import com.httydcraft.vk.api.velocity.VelocityVkApiPlugin;
import com.httydcraft.vk.api.velocity.events.*;
import com.vk.api.sdk.objects.audio.Audio;
import com.vk.api.sdk.objects.board.TopicComment;
import com.vk.api.sdk.objects.callback.*;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.video.Video;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.Wallpost;

/**
 * Parser for VK longpoll events in a Velocity environment.
 */
public class VelocityLongpoolEventParser extends AbstractLongpoolEventParser {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private final VelocityVkApiPlugin plugin;
	// endregion

	// region Constructors
	/**
	 * Constructs a VelocityLongpoolEventParser with the specified plugin.
	 *
	 * @param plugin the Velocity VK API plugin
	 * @throws IllegalArgumentException if plugin is null
	 */
	public VelocityLongpoolEventParser(VelocityVkApiPlugin plugin) {
		Preconditions.checkNotNull(plugin, "Plugin cannot be null");
		this.plugin = plugin;
	}
	// endregion

	// region Event Handlers
	/**
	 * Handles a new message event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param message the new message object
	 */
	@Override
	public void messageNew(Integer groupId, String secret, Message message) {
		Preconditions.checkNotNull(message, "Message cannot be null");
		VKMessageEvent event = new VKMessageEvent(message, groupId);
		fireEventWithResult(event, groupId, () -> plugin.getVkApiProvider().deleteMessage(message));
		LOGGER.atFine().log("Processed new message event for group ID: %d", groupId);
	}

	/**
	 * Handles a message reply event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param message the reply message object
	 */
	@Override
	public void messageReply(Integer groupId, String secret, Message message) {
		Preconditions.checkNotNull(message, "Message cannot be null");
		VKMessageReplyEvent event = new VKMessageReplyEvent(message, groupId);
		fireEventWithResult(event, groupId, () -> plugin.getVkApiProvider().deleteMessage(message));
		LOGGER.atFine().log("Processed message reply event for group ID: %d", groupId);
	}

	/**
	 * Handles a message edit event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for validation
	 * @param messageEdit the edited message object
	 */
	@Override
	public void messageEdit(Integer groupId, String secret, Message messageEdit) {
		Preconditions.checkNotNull(messageEdit, "Message edit cannot be null");
		fireEvent(new VKMessageEditEvent(messageEdit, groupId), groupId);
		LOGGER.atFine().log("Processed message edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a message allow event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param messageAllow the message allow object
	 */
	@Override
	public void messageAllow(Integer groupId, String secret, MessageAllow messageAllow) {
		Preconditions.checkNotNull(messageAllow, "Message allow cannot be null");
		fireEvent(new VKMessageAllowEvent(messageAllow, groupId), groupId);
		LOGGER.atFine().log("Processed message allow event for group ID: %d", groupId);
	}

	/**
	 * Handles a message deny event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for validation
	 * @param messageDeny the message deny object
	 */
	@Override
	public void messageDeny(Integer groupId, String secret, MessageDeny messageDeny) {
		Preconditions.checkNotNull(messageDeny, "Message deny cannot be null");
		fireEvent(new VKMessageDenyEvent(messageDeny, groupId), groupId);
		LOGGER.atFine().log("Processed message deny event for group ID: %d", groupId);
	}

	/**
	 * Handles a message typing event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for validation
	 * @param messageTyping the message typing object
	 */
	@Override
	public void messageTyping(Integer groupId, String secret, MessageTyping messageTyping) {
		Preconditions.checkNotNull(messageTyping, "Message typing cannot be null");
		fireEvent(new VKMessageTypingEvent(messageTyping, groupId), groupId);
		LOGGER.atFine().log("Processed message typing event for group ID: %d", groupId);
	}

	/**
	 * Handles a callback button press event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for validation
	 * @param buttonEvent the callback button event object
	 */
	@Override
	public void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent) {
		Preconditions.checkNotNull(buttonEvent, "Button event cannot be null");
		fireEvent(new VKCallbackButtonPressEvent(buttonEvent, groupId), groupId);
		LOGGER.atFine().log("Processed callback button press event for group ID: %d", groupId);
	}

	/**
	 * Handles a new photo event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param photo   the new photo object
	 */
	@Override
	public void photoNew(Integer groupId, String secret, Photo photo) {
		Preconditions.checkNotNull(photo, "Photo cannot be null");
		fireEvent(new VKPhotoNewEvent(photo, groupId), groupId);
		LOGGER.atFine().log("Processed new photo event for group ID: %d", groupId);
	}

	/**
	 * Handles a new photo comment event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the new photo comment object
	 */
	@Override
	public void photoCommentNew(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(comment, "Photo comment cannot be null");
		fireEvent(new VKPhotoCommentEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed new photo comment event for group ID: %d", groupId);
	}

	/**
	 * Handles a photo comment edit event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the edited photo comment object
	 */
	@Override
	public void photoCommentEdit(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(comment, "Photo comment cannot be null");
		fireEvent(new VKPhotoCommentEditEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed photo comment edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a photo comment restore event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the restored photo comment object
	 */
	@Override
	public void photoCommentRestore(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(comment, "Photo comment cannot be null");
		fireEvent(new VKPhotoCommentRestoreEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed photo comment restore event for group ID: %d", groupId);
	}

	/**
	 * Handles a photo comment delete event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param commentDelete the photo comment deletion object
	 */
	@Override
	public void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete) {
		Preconditions.checkNotNull(commentDelete, "Photo comment delete cannot be null");
		fireEvent(new VKPhotoCommentDeleteEvent(commentDelete, groupId), groupId);
		LOGGER.atFine().log("Processed photo comment delete event for group ID: %d", groupId);
	}

	/**
	 * Handles a new audio event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param audio   the new audio object
	 */
	@Override
	public void audioNew(Integer groupId, String secret, Audio audio) {
		Preconditions.checkNotNull(audio, "Audio cannot be null");
		fireEvent(new VKAudioNewEvent(audio, groupId), groupId);
		LOGGER.atFine().log("Processed new audio event for group ID: %d", groupId);
	}

	/**
	 * Handles a new video event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param video   the new video object
	 */
	@Override
	public void videoNew(Integer groupId, String secret, Video video) {
		Preconditions.checkNotNull(video, "Video cannot be null");
		fireEvent(new VKVideoNewEvent(video, groupId), groupId);
		LOGGER.atFine().log("Processed new video event for group ID: %d", groupId);
	}

	/**
	 * Handles a new video comment event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param videoComment the new video comment object
	 */
	@Override
	public void videoCommentNew(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		fireEvent(new VKVideoCommentEvent(videoComment, groupId), groupId);
		LOGGER.atFine().log("Processed new video comment event for group ID: %d", groupId);
	}

	/**
	 * Handles a video comment edit event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param videoComment the edited video comment object
	 */
	@Override
	public void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		fireEvent(new VKVideoCommentEditEvent(videoComment, groupId), groupId);
		LOGGER.atFine().log("Processed video comment edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a video comment restore event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param videoComment the restored video comment object
	 */
	@Override
	public void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		fireEvent(new VKVideoCommentRestoreEvent(videoComment, groupId), groupId);
		LOGGER.atFine().log("Processed video comment restore event for group ID: %d", groupId);
	}

	/**
	 * Handles a video comment delete event.
	 *
	 * @param groupId           the ID of the VK group
	 * @param secret            the secret key for validation
	 * @param videoCommentDelete the video comment deletion object
	 */
	@Override
	public void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete) {
		Preconditions.checkNotNull(videoCommentDelete, "Video comment delete cannot be null");
		fireEvent(new VKVideoCommentDeleteEvent(videoCommentDelete, groupId), groupId);
		LOGGER.atFine().log("Processed video comment delete event for group ID: %d", groupId);
	}

	/**
	 * Handles a new wall post event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param post    the new wall post object
	 */
	@Override
	public void wallPostNew(Integer groupId, String secret, Wallpost post) {
		Preconditions.checkNotNull(post, "Wall post cannot be null");
		fireEvent(new VKPostNewEvent(post, groupId), groupId);
		LOGGER.atFine().log("Processed new wall post event for group ID: %d", groupId);
	}

	/**
	 * Handles a wall post repost event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param post    the reposted wall post object
	 */
	@Override
	public void wallRepost(Integer groupId, String secret, Wallpost post) {
		Preconditions.checkNotNull(post, "Wall post cannot be null");
		fireEvent(new VKPostRepostEvent(post, groupId), groupId);
		LOGGER.atFine().log("Processed wall repost event for group ID: %d", groupId);
	}

	/**
	 * Handles a new wall reply event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for validation
	 * @param postComment the new wall comment object
	 */
	@Override
	public void wallReplyNew(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(postComment, "Wall comment cannot be null");
		fireEvent(new VKPostReplyEvent(postComment, groupId), groupId);
		LOGGER.atFine().log("Processed new wall reply event for group ID: %d", groupId);
	}

	/**
	 * Handles a wall reply edit event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for validation
	 * @param postComment the edited wall comment object
	 */
	@Override
	public void wallReplyEdit(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(postComment, "Wall comment cannot be null");
		fireEvent(new VKPostReplyEditEvent(postComment, groupId), groupId);
		LOGGER.atFine().log("Processed wall reply edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a wall reply restore event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for validation
	 * @param postComment the restored wall comment object
	 */
	@Override
	public void wallReplyRestore(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(postComment, "Wall comment cannot be null");
		fireEvent(new VKPostReplyRestoreEvent(postComment, groupId), groupId);
		LOGGER.atFine().log("Processed wall reply restore event for group ID: %d", groupId);
	}

	/**
	 * Handles a wall reply delete event.
	 *
	 * @param groupId          the ID of the VK group
	 * @param secret           the secret key for validation
	 * @param postCommentDelete the wall comment deletion object
	 */
	@Override
	public void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete) {
		Preconditions.checkNotNull(postCommentDelete, "Wall comment delete cannot be null");
		fireEvent(new VKPostReplyDeleteEvent(postCommentDelete, groupId), groupId);
		LOGGER.atFine().log("Processed wall reply delete event for group ID: %d", groupId);
	}

	/**
	 * Handles a like add event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param likeAdd the like add/remove object
	 */
	@Override
	public void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd) {
		Preconditions.checkNotNull(likeAdd, "Like add cannot be null");
		fireEvent(new VKLikeAddEvent(likeAdd, groupId), groupId);
		LOGGER.atFine().log("Processed like add event for group ID: %d", groupId);
	}

	/**
	 * Handles a like remove event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for validation
	 * @param likeRemove the like add/remove object
	 */
	@Override
	public void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove) {
		Preconditions.checkNotNull(likeRemove, "Like remove cannot be null");
		fireEvent(new VKLikeRemoveEvent(likeRemove, groupId), groupId);
		LOGGER.atFine().log("Processed like remove event for group ID: %d", groupId);
	}

	/**
	 * Handles a new board post event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param topicComment the new topic comment object
	 */
	@Override
	public void boardPostNew(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		fireEvent(new VKBoardNewEvent(topicComment, groupId), groupId);
		LOGGER.atFine().log("Processed new board post event for group ID: %d", groupId);
	}

	/**
	 * Handles a board post edit event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param topicComment the edited topic comment object
	 */
	@Override
	public void boardPostEdit(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		fireEvent(new VKBoardEditEvent(topicComment, groupId), groupId);
		LOGGER.atFine().log("Processed board post edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a board post restore event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param topicComment the restored topic comment object
	 */
	@Override
	public void boardPostRestore(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		fireEvent(new VKBoardRestoreEvent(topicComment, groupId), groupId);
		LOGGER.atFine().log("Processed board post restore event for group ID: %d", groupId);
	}

	/**
	 * Handles a board post delete event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for validation
	 * @param boardDelete the board post deletion object
	 */
	@Override
	public void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete) {
		Preconditions.checkNotNull(boardDelete, "Board delete cannot be null");
		fireEvent(new VKBoardDeleteEvent(boardDelete, groupId), groupId);
		LOGGER.atFine().log("Processed board post delete event for group ID: %d", groupId);
	}

	/**
	 * Handles a new market comment event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the new market comment object
	 */
	@Override
	public void marketCommentNew(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(comment, "Market comment cannot be null");
		fireEvent(new VKMarketCommentEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed new market comment event for group ID: %d", groupId);
	}

	/**
	 * Handles a market comment edit event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the edited market comment object
	 */
	@Override
	public void marketCommentEdit(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(comment, "Market comment cannot be null");
		fireEvent(new VKMarketCommentEditEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed market comment edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a market comment restore event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param comment the restored market comment object
	 */
	@Override
	public void marketCommentRestore(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(comment, "Market comment cannot be null");
		fireEvent(new VKMarketCommentRestoreEvent(comment, groupId), groupId);
		LOGGER.atFine().log("Processed market comment restore event for group ID: %d", groupId);
	}

	/**
	 * Handles a market comment delete event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for validation
	 * @param commentDelete the market comment deletion object
	 */
	@Override
	public void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete) {
		Preconditions.checkNotNull(commentDelete, "Market comment delete cannot be null");
		fireEvent(new VKMarketCommentDeleteEvent(commentDelete, groupId), groupId);
		LOGGER.atFine().log("Processed market comment delete event for group ID: %d", groupId);
	}

	/**
	 * Handles a group leave event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for validation
	 * @param groupLeave the group leave object
	 */
	@Override
	public void groupLeave(Integer groupId, String secret, GroupLeave groupLeave) {
		Preconditions.checkNotNull(groupLeave, "Group leave cannot be null");
		fireEvent(new VKUserGroupLeaveEvent(groupLeave, groupId), groupId);
		LOGGER.atFine().log("Processed group leave event for group ID: %d", groupId);
	}

	/**
	 * Handles a group join event.
	 *
	 * @param groupId   the ID of the VK group
	 * @param secret    the secret key for validation
	 * @param groupJoin the group join object
	 */
	@Override
	public void groupJoin(Integer groupId, String secret, GroupJoin groupJoin) {
		Preconditions.checkNotNull(groupJoin, "Group join cannot be null");
		fireEvent(new VKUserGroupJoinEvent(groupJoin, groupId), groupId);
		LOGGER.atFine().log("Processed group join event for group ID: %d", groupId);
	}

	/**
	 * Handles a group settings change event.
	 *
	 * @param groupId        the ID of the VK group
	 * @param secret         the secret key for validation
	 * @param changeSettings the group settings change object
	 */
	@Override
	public void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings) {
		Preconditions.checkNotNull(changeSettings, "Change settings cannot be null");
		fireEvent(new VKGroupChangeSettingsEvent(changeSettings, groupId), groupId);
		LOGGER.atFine().log("Processed group settings change event for group ID: %d", groupId);
	}

	/**
	 * Handles a group photo change event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for validation
	 * @param changePhoto the group photo change object
	 */
	@Override
	public void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto) {
		Preconditions.checkNotNull(changePhoto, "Change photo cannot be null");
		fireEvent(new VKGroupChangePhotoEvent(changePhoto, groupId), groupId);
		LOGGER.atFine().log("Processed group photo change event for group ID: %d", groupId);
	}

	/**
	 * Handles a group officers edit event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for validation
	 * @param officersEdit the group officers edit object
	 */
	@Override
	public void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit) {
		Preconditions.checkNotNull(officersEdit, "Officers edit cannot be null");
		fireEvent(new VKGroupOfficersEditEvent(officersEdit, groupId), groupId);
		LOGGER.atFine().log("Processed group officers edit event for group ID: %d", groupId);
	}

	/**
	 * Handles a new poll vote event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param vote    the poll vote object
	 */
	@Override
	public void pollVoteNew(Integer groupId, String secret, PollVoteNew vote) {
		Preconditions.checkNotNull(vote, "Poll vote cannot be null");
		fireEvent(new VKPollVoteNewEvent(vote, groupId), groupId);
		LOGGER.atFine().log("Processed new poll vote event for group ID: %d", groupId);
	}

	/**
	 * Handles a user block event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param block   the user block object
	 */
	@Override
	public void userBlock(Integer groupId, String secret, UserBlock block) {
		Preconditions.checkNotNull(block, "User block cannot be null");
		fireEvent(new VKUserBlockEvent(block, groupId), groupId);
		LOGGER.atFine().log("Processed user block event for group ID: %d", groupId);
	}

	/**
	 * Handles a user unblock event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param unblock the user unblock object
	 */
	@Override
	public void userUnblock(Integer groupId, String secret, UserUnblock unblock) {
		Preconditions.checkNotNull(unblock, "User unblock cannot be null");
		fireEvent(new VKUserUnblockEvent(unblock, groupId), groupId);
		LOGGER.atFine().log("Processed user unblock event for group ID: %d", groupId);
	}

	/**
	 * Handles a confirmation event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 */
	@Override
	public void confirmation(Integer groupId, String secret) {
		LOGGER.atFine().log("Processed confirmation event for group ID: %d ^-^", groupId);
	}

	/**
	 * Handles a generic JSON event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for validation
	 * @param json    the JSON object
	 */
	@Override
	public void jsonEvent(Integer groupId, String secret, JsonObject json) {
		Preconditions.checkNotNull(json, "JSON object cannot be null");
		fireEvent(new VKJsonEvent(json, groupId), groupId);
		LOGGER.atFine().log("Processed JSON event for group ID: %d", groupId);
	}
	// endregion

	// region Private Methods
	/**
	 * Fires an event using the Velocity event manager.
	 *
	 * @param event   the event to fire
	 * @param groupId the ID of the VK group for logging context
	 * @throws IllegalArgumentException if event is null
	 */
	private void fireEvent(AbstractVkEvent event, Integer groupId) {
		Preconditions.checkNotNull(event, "Event cannot be null");
		plugin.getProxyServer().getEventManager().fire(event).exceptionally(throwable -> {
			LOGGER.atSevere().withCause(throwable).log("Failed to fire event %s for group ID: %d",
					event.getClass().getSimpleName(), groupId);
			return null;
		});
	}

	/**
	 * Fires an event with a result check, executing an action if the event is not allowed.
	 *
	 * @param event   the event to fire
	 * @param groupId the ID of the VK group for logging context
	 * @param action  the action to execute if the event is not allowed
	 * @throws IllegalArgumentException if event or action is null
	 */
	private void fireEventWithResult(VKMessageUpdateEvent event, Integer groupId, Runnable action) {
		Preconditions.checkNotNull(event, "Event cannot be null");
		Preconditions.checkNotNull(action, "Action cannot be null");
		plugin.getProxyServer().getEventManager().fire(event).thenAccept(firedEvent -> {
			if (!firedEvent.getResult().isAllowed()) {
				action.run();
				LOGGER.atFine().log("Executed action for disallowed event %s for group ID: %d",
						event.getClass().getSimpleName(), groupId);
			}
		}).exceptionally(throwable -> {
			LOGGER.atSevere().withCause(throwable).log("Failed to fire event %s for group ID: %d",
					event.getClass().getSimpleName(), groupId);
			return null;
		});
	}
	// endregion
}