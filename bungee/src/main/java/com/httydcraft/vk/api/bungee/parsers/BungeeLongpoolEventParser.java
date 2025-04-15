package com.httydcraft.vk.api.bungee.parsers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.google.gson.JsonObject;
import com.httydcraft.vk.api.core.api.VkApiPlugin;
import com.httydcraft.vk.api.core.api.parsers.AbstractLongpoolEventParser;
import com.httydcraft.vk.api.core.api.parsers.objects.CallbackButtonEvent;
import com.httydcraft.vk.api.core.api.parsers.objects.MessageTyping;
import com.httydcraft.vk.api.bungee.events.*;
import com.vk.api.sdk.objects.audio.Audio;
import com.vk.api.sdk.objects.board.TopicComment;
import com.vk.api.sdk.objects.callback.*;
import com.vk.api.sdk.objects.messages.Message;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.video.Video;
import com.vk.api.sdk.objects.wall.WallComment;
import com.vk.api.sdk.objects.wall.Wallpost;

/**
 * Parser for handling VK longpoll events in a Bungee environment.
 */
public class BungeeLongpoolEventParser extends AbstractLongpoolEventParser {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private final VkApiPlugin plugin;
	// endregion

	// region Constructors
	/**
	 * Constructs a new longpoll event parser with the specified VK API plugin.
	 *
	 * @param plugin the VK API plugin instance
	 * @throws IllegalArgumentException if plugin is null
	 */
	public BungeeLongpoolEventParser(VkApiPlugin plugin) {
		this.plugin = Preconditions.checkNotNull(plugin, "Plugin cannot be null");
		LOGGER.atFine().log("Initialized %s", this.getClass().getSimpleName());
	}
	// endregion

	// region Public Methods
	/**
	 * Handles a new message event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param message the new message object
	 * @throws IllegalArgumentException if groupId or message is null
	 */
	@Override
	public void messageNew(Integer groupId, String secret, Message message) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(message, "Message cannot be null");
		LOGGER.atFine().log("Processing new message for group: %d", groupId);
		try {
			VKMessageEvent messageEvent = new VKMessageEvent(message, groupId);
			plugin.callEvent(messageEvent);
			if (messageEvent.isCancelled()) {
				plugin.getVkApiProvider().deleteMessage(message);
				LOGGER.atInfo().log("Deleted cancelled message for group: %d", groupId);
			}
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageNew event");
			throw e;
		}
	}

	/**
	 * Handles a message reply event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param message the reply message object
	 * @throws IllegalArgumentException if groupId or message is null
	 */
	@Override
	public void messageReply(Integer groupId, String secret, Message message) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(message, "Message cannot be null");
		LOGGER.atFine().log("Processing message reply for group: %d", groupId);
		try {
			VKMessageReplyEvent replyEvent = new VKMessageReplyEvent(message, groupId);
			plugin.callEvent(replyEvent);
			if (replyEvent.isCancelled()) {
				plugin.getVkApiProvider().deleteMessage(message);
				LOGGER.atInfo().log("Deleted cancelled reply message for group: %d", groupId);
			}
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageReply event");
			throw e;
		}
	}

	/**
	 * Handles a message edit event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param messageEdit the edited message object
	 * @throws IllegalArgumentException if groupId or messageEdit is null
	 */
	@Override
	public void messageEdit(Integer groupId, String secret, Message messageEdit) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(messageEdit, "Message edit cannot be null");
		LOGGER.atFine().log("Processing message edit for group: %d", groupId);
		try {
			new VKMessageEditEvent(messageEdit, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageEdit event");
			throw e;
		}
	}

	/**
	 * Handles a message allow event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param messageAllow the message allow object
	 * @throws IllegalArgumentException if groupId or messageAllow is null
	 */
	@Override
	public void messageAllow(Integer groupId, String secret, MessageAllow messageAllow) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(messageAllow, "Message allow cannot be null");
		LOGGER.atFine().log("Processing message allow for group: %d", groupId);
		try {
			new VKMessageAllowEvent(messageAllow, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageAllow event");
			throw e;
		}
	}

	/**
	 * Handles a message deny event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param messageDeny the message deny object
	 * @throws IllegalArgumentException if groupId or messageDeny is null
	 */
	@Override
	public void messageDeny(Integer groupId, String secret, MessageDeny messageDeny) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(messageDeny, "Message deny cannot be null");
		LOGGER.atFine().log("Processing message deny for group: %d", groupId);
		try {
			new VKMessageDenyEvent(messageDeny, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageDeny event");
			throw e;
		}
	}

	/**
	 * Handles a message typing event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for verification
	 * @param messageTyping the message typing object
	 * @throws IllegalArgumentException if groupId or messageTyping is null
	 */
	@Override
	public void messageTyping(Integer groupId, String secret, MessageTyping messageTyping) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(messageTyping, "Message typing cannot be null");
		LOGGER.atFine().log("Processing message typing for group: %d", groupId);
		try {
			new VKMessageTypingEvent(messageTyping, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageTyping event");
			throw e;
		}
	}

	/**
	 * Handles a callback button press event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param buttonEvent the callback button event object
	 * @throws IllegalArgumentException if groupId or buttonEvent is null
	 */
	@Override
	public void messageEvent(Integer groupId, String secret, CallbackButtonEvent buttonEvent) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(buttonEvent, "Button event cannot be null");
		LOGGER.atFine().log("Processing button press for group: %d", groupId);
		try {
			new VKCallbackButtonPressEvent(buttonEvent, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process messageEvent event");
			throw e;
		}
	}

	/**
	 * Handles a new photo event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param photo   the photo object
	 * @throws IllegalArgumentException if groupId or photo is null
	 */
	@Override
	public void photoNew(Integer groupId, String secret, Photo photo) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(photo, "Photo cannot be null");
		LOGGER.atFine().log("Processing new photo for group: %d", groupId);
		try {
			new VKPhotoNewEvent(photo, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process photoNew event");
			throw e;
		}
	}

	/**
	 * Handles a new photo comment event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the photo comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void photoCommentNew(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing new photo comment for group: %d", groupId);
		try {
			new VKPhotoCommentEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process photoCommentNew event");
			throw e;
		}
	}

	/**
	 * Handles a photo comment edit event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the edited photo comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void photoCommentEdit(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing photo comment edit for group: %d", groupId);
		try {
			new VKPhotoCommentEditEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process photoCommentEdit event");
			throw e;
		}
	}

	/**
	 * Handles a photo comment restore event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the restored photo comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void photoCommentRestore(Integer groupId, String secret, PhotoComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing photo comment restore for group: %d", groupId);
		try {
			new VKPhotoCommentRestoreEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process photoCommentRestore event");
			throw e;
		}
	}

	/**
	 * Handles a photo comment delete event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for verification
	 * @param commentDelete the photo comment deletion object
	 * @throws IllegalArgumentException if groupId or commentDelete is null
	 */
	@Override
	public void photoCommentDelete(Integer groupId, String secret, PhotoCommentDelete commentDelete) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(commentDelete, "Comment delete cannot be null");
		LOGGER.atFine().log("Processing photo comment delete for group: %d", groupId);
		try {
			new VKPhotoCommentDeleteEvent(commentDelete, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process photoCommentDelete event");
			throw e;
		}
	}

	/**
	 * Handles a new audio event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param audio   the audio object
	 * @throws IllegalArgumentException if groupId or audio is null
	 */
	@Override
	public void audioNew(Integer groupId, String secret, Audio audio) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(audio, "Audio cannot be null");
		LOGGER.atFine().log("Processing new audio for group: %d", groupId);
		try {
			new VKAudioNewEvent(audio, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process audioNew event");
			throw e;
		}
	}

	/**
	 * Handles a new video event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param video   the video object
	 * @throws IllegalArgumentException if groupId or video is null
	 */
	@Override
	public void videoNew(Integer groupId, String secret, Video video) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(video, "Video cannot be null");
		LOGGER.atFine().log("Processing new video for group: %d", groupId);
		try {
			new VKVideoNewEvent(video, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process videoNew event");
			throw e;
		}
	}

	/**
	 * Handles a new video comment event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param videoComment the video comment object
	 * @throws IllegalArgumentException if groupId or videoComment is null
	 */
	@Override
	public void videoCommentNew(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		LOGGER.atFine().log("Processing new video comment for group: %d", groupId);
		try {
			new VKVideoCommentEvent(videoComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process videoCommentNew event");
			throw e;
		}
	}

	/**
	 * Handles a video comment edit event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param videoComment the edited video comment object
	 * @throws IllegalArgumentException if groupId or videoComment is null
	 */
	@Override
	public void videoCommentEdit(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		LOGGER.atFine().log("Processing video comment edit for group: %d", groupId);
		try {
			new VKVideoCommentEditEvent(videoComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process videoCommentEdit event");
			throw e;
		}
	}

	/**
	 * Handles a video comment restore event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param videoComment the restored video comment object
	 * @throws IllegalArgumentException if groupId or videoComment is null
	 */
	@Override
	public void videoCommentRestore(Integer groupId, String secret, VideoComment videoComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(videoComment, "Video comment cannot be null");
		LOGGER.atFine().log("Processing video comment restore for group: %d", groupId);
		try {
			new VKVideoCommentRestoreEvent(videoComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process videoCommentRestore event");
			throw e;
		}
	}

	/**
	 * Handles a video comment delete event.
	 *
	 * @param groupId           the ID of the VK group
	 * @param secret            the secret key for verification
	 * @param videoCommentDelete the video comment deletion object
	 * @throws IllegalArgumentException if groupId or videoCommentDelete is null
	 */
	@Override
	public void videoCommentDelete(Integer groupId, String secret, VideoCommentDelete videoCommentDelete) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(videoCommentDelete, "Video comment delete cannot be null");
		LOGGER.atFine().log("Processing video comment delete for group: %d", groupId);
		try {
			new VKVideoCommentDeleteEvent(videoCommentDelete, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process videoCommentDelete event");
			throw e;
		}
	}

	/**
	 * Handles a new wall post event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param post    the wall post object
	 * @throws IllegalArgumentException if groupId or post is null
	 */
	@Override
	public void wallPostNew(Integer groupId, String secret, Wallpost post) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(post, "Post cannot be null");
		LOGGER.atFine().log("Processing new wall post for group: %d", groupId);
		try {
			new VKPostNewEvent(post, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallPostNew event");
			throw e;
		}
	}

	/**
	 * Handles a wall repost event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param post    the reposted wall post object
	 * @throws IllegalArgumentException if groupId or post is null
	 */
	@Override
	public void wallRepost(Integer groupId, String secret, Wallpost post) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(post, "Post cannot be null");
		LOGGER.atFine().log("Processing wall repost for group: %d", groupId);
		try {
			new VKPostRepostEvent(post, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallRepost event");
			throw e;
		}
	}

	/**
	 * Handles a new wall reply event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param postComment the wall comment object
	 * @throws IllegalArgumentException if groupId or postComment is null
	 */
	@Override
	public void wallReplyNew(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(postComment, "Post comment cannot be null");
		LOGGER.atFine().log("Processing new wall reply for group: %d", groupId);
		try {
			new VKPostReplyEvent(postComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallReplyNew event");
			throw e;
		}
	}

	/**
	 * Handles a wall reply edit event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param postComment the edited wall comment object
	 * @throws IllegalArgumentException if groupId or postComment is null
	 */
	@Override
	public void wallReplyEdit(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(postComment, "Post comment cannot be null");
		LOGGER.atFine().log("Processing wall reply edit for group: %d", groupId);
		try {
			new VKPostReplyEditEvent(postComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallReplyEdit event");
			throw e;
		}
	}

	/**
	 * Handles a wall reply restore event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param postComment the restored wall comment object
	 * @throws IllegalArgumentException if groupId or postComment is null
	 */
	@Override
	public void wallReplyRestore(Integer groupId, String secret, WallComment postComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(postComment, "Post comment cannot be null");
		LOGGER.atFine().log("Processing wall reply restore for group: %d", groupId);
		try {
			new VKPostReplyRestoreEvent(postComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallReplyRestore event");
			throw e;
		}
	}

	/**
	 * Handles a wall reply delete event.
	 *
	 * @param groupId          the ID of the VK group
	 * @param secret           the secret key for verification
	 * @param postCommentDelete the wall comment deletion object
	 * @throws IllegalArgumentException if groupId or postCommentDelete is null
	 */
	@Override
	public void wallReplyDelete(Integer groupId, String secret, WallCommentDelete postCommentDelete) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(postCommentDelete, "Post comment delete cannot be null");
		LOGGER.atFine().log("Processing wall reply delete for group: %d", groupId);
		try {
			new VKPostReplyDeleteEvent(postCommentDelete, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process wallReplyDelete event");
			throw e;
		}
	}

	/**
	 * Handles a like add event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param likeAdd the like add/remove object
	 * @throws IllegalArgumentException if groupId or likeAdd is null
	 */
	@Override
	public void likeAdd(Integer groupId, String secret, LikeAddRemove likeAdd) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(likeAdd, "Like add cannot be null");
		LOGGER.atFine().log("Processing like add for group: %d", groupId);
		try {
			new VKLikeAddEvent(likeAdd, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process likeAdd event");
			throw e;
		}
	}

	/**
	 * Handles a like remove event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for verification
	 * @param likeRemove the like add/remove object
	 * @throws IllegalArgumentException if groupId or likeRemove is null
	 */
	@Override
	public void likeRemove(Integer groupId, String secret, LikeAddRemove likeRemove) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(likeRemove, "Like remove cannot be null");
		LOGGER.atFine().log("Processing like remove for group: %d", groupId);
		try {
			new VKLikeRemoveEvent(likeRemove, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process likeRemove event");
			throw e;
		}
	}

	/**
	 * Handles a new board post event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param topicComment the topic comment object
	 * @throws IllegalArgumentException if groupId or topicComment is null
	 */
	@Override
	public void boardPostNew(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		LOGGER.atFine().log("Processing new board post for group: %d", groupId);
		try {
			new VKBoardNewEvent(topicComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process boardPostNew event");
			throw e;
		}
	}

	/**
	 * Handles a board post edit event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param topicComment the edited topic comment object
	 * @throws IllegalArgumentException if groupId or topicComment is null
	 */
	@Override
	public void boardPostEdit(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		LOGGER.atFine().log("Processing board post edit for group: %d", groupId);
		try {
			new VKBoardEditEvent(topicComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process boardPostEdit event");
			throw e;
		}
	}

	/**
	 * Handles a board post restore event.
	 *
	 * @param groupId      the ID of the VK group
	 * @param secret       the secret key for verification
	 * @param topicComment the restored topic comment object
	 * @throws IllegalArgumentException if groupId or topicComment is null
	 */
	@Override
	public void boardPostRestore(Integer groupId, String secret, TopicComment topicComment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(topicComment, "Topic comment cannot be null");
		LOGGER.atFine().log("Processing board post restore for group: %d", groupId);
		try {
			new VKBoardRestoreEvent(topicComment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process boardPostRestore event");
			throw e;
		}
	}

	/**
	 * Handles a board post delete event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param boardDelete the board post deletion object
	 * @throws IllegalArgumentException if groupId or boardDelete is null
	 */
	@Override
	public void boardPostDelete(Integer groupId, String secret, BoardPostDelete boardDelete) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(boardDelete, "Board delete cannot be null");
		LOGGER.atFine().log("Processing board post delete for group: %d", groupId);
		try {
			new VKBoardDeleteEvent(boardDelete, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process boardPostDelete event");
			throw e;
		}
	}

	/**
	 * Handles a new market comment event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the market comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void marketCommentNew(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing new market comment for group: %d", groupId);
		try {
			new VKMarketCommentEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process marketCommentNew event");
			throw e;
		}
	}

	/**
	 * Handles a market comment edit event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the edited market comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void marketCommentEdit(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing market comment edit for group: %d", groupId);
		try {
			new VKMarketCommentEditEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process marketCommentEdit event");
			throw e;
		}
	}

	/**
	 * Handles a market comment restore event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param comment the restored market comment object
	 * @throws IllegalArgumentException if groupId or comment is null
	 */
	@Override
	public void marketCommentRestore(Integer groupId, String secret, MarketComment comment) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(comment, "Comment cannot be null");
		LOGGER.atFine().log("Processing market comment restore for group: %d", groupId);
		try {
			new VKMarketCommentRestoreEvent(comment, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process marketCommentRestore event");
			throw e;
		}
	}

	/**
	 * Handles a market comment delete event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for verification
	 * @param commentDelete the market comment deletion object
	 * @throws IllegalArgumentException if groupId or commentDelete is null
	 */
	@Override
	public void marketCommentDelete(Integer groupId, String secret, MarketCommentDelete commentDelete) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(commentDelete, "Comment delete cannot be null");
		LOGGER.atFine().log("Processing market comment delete for group: %d", groupId);
		try {
			new VKMarketCommentDeleteEvent(commentDelete, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process marketCommentDelete event");
			throw e;
		}
	}

	/**
	 * Handles a group leave event.
	 *
	 * @param groupId    the ID of the VK group
	 * @param secret     the secret key for verification
	 * @param groupLeave the group leave object
	 * @throws IllegalArgumentException if groupId or groupLeave is null
	 */
	@Override
	public void groupLeave(Integer groupId, String secret, GroupLeave groupLeave) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(groupLeave, "Group leave cannot be null");
		LOGGER.atFine().log("Processing group leave for group: %d", groupId);
		try {
			new VKUserGroupLeaveEvent(groupLeave, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process groupLeave event");
			throw e;
		}
	}

	/**
	 * Handles a group join event.
	 *
	 * @param groupId   the ID of the VK group
	 * @param secret    the secret key for verification
	 * @param groupJoin the group join object
	 * @throws IllegalArgumentException if groupId or groupJoin is null
	 */
	@Override
	public void groupJoin(Integer groupId, String secret, GroupJoin groupJoin) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(groupJoin, "Group join cannot be null");
		LOGGER.atFine().log("Processing group join for group: %d", groupId);
		try {
			new VKUserGroupJoinEvent(groupJoin, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process groupJoin event");
			throw e;
		}
	}

	/**
	 * Handles a group settings change event.
	 *
	 * @param groupId        the ID of the VK group
	 * @param secret         the secret key for verification
	 * @param changeSettings the group settings change object
	 * @throws IllegalArgumentException if groupId or changeSettings is null
	 */
	@Override
	public void groupChangeSettings(Integer groupId, String secret, GroupChangeSettings changeSettings) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(changeSettings, "Change settings cannot be null");
		LOGGER.atFine().log("Processing group settings change for group: %d", groupId);
		try {
			new VKGroupChangeSettingsEvent(changeSettings, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process groupChangeSettings event");
			throw e;
		}
	}

	/**
	 * Handles a group photo change event.
	 *
	 * @param groupId     the ID of the VK group
	 * @param secret      the secret key for verification
	 * @param changePhoto the group photo change object
	 * @throws IllegalArgumentException if groupId or changePhoto is null
	 */
	@Override
	public void groupChangePhoto(Integer groupId, String secret, GroupChangePhoto changePhoto) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(changePhoto, "Change photo cannot be null");
		LOGGER.atFine().log("Processing group photo change for group: %d", groupId);
		try {
			new VKGroupChangePhotoEvent(changePhoto, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process groupChangePhoto event");
			throw e;
		}
	}

	/**
	 * Handles a group officers edit event.
	 *
	 * @param groupId       the ID of the VK group
	 * @param secret        the secret key for verification
	 * @param officersEdit  the group officers edit object
	 * @throws IllegalArgumentException if groupId or officersEdit is null
	 */
	@Override
	public void groupOfficersEdit(Integer groupId, String secret, GroupOfficersEdit officersEdit) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(officersEdit, "Officers edit cannot be null");
		LOGGER.atFine().log("Processing group officers edit for group: %d", groupId);
		try {
			new VKGroupOfficersEditEvent(officersEdit, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process groupOfficersEdit event");
			throw e;
		}
	}

	/**
	 * Handles a new poll vote event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param vote    the poll vote object
	 * @throws IllegalArgumentException if groupId or vote is null
	 */
	@Override
	public void pollVoteNew(Integer groupId, String secret, PollVoteNew vote) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(vote, "Vote cannot be null");
		LOGGER.atFine().log("Processing new poll vote for group: %d", groupId);
		try {
			new VKPollVoteNewEvent(vote, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process pollVoteNew event");
			throw e;
		}
	}

	/**
	 * Handles a user block event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param block   the user block object
	 * @throws IllegalArgumentException if groupId or block is null
	 */
	@Override
	public void userBlock(Integer groupId, String secret, UserBlock block) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(block, "Block cannot be null");
		LOGGER.atFine().log("Processing user block for group: %d", groupId);
		try {
			new VKUserBlockEvent(block, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process userBlock event");
			throw e;
		}
	}

	/**
	 * Handles a user unblock event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param unblock the user unblock object
	 * @throws IllegalArgumentException if groupId or unblock is null
	 */
	@Override
	public void userUnblock(Integer groupId, String secret, UserUnblock unblock) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(unblock, "Unblock cannot be null");
		LOGGER.atFine().log("Processing user unblock for group: %d", groupId);
		try {
			new VKUserUnblockEvent(unblock, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process userUnblock event");
			throw e;
		}
	}

	/**
	 * Handles a confirmation event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @throws IllegalArgumentException if groupId is null
	 */
	@Override
	public void confirmation(Integer groupId, String secret) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		LOGGER.atFine().log("Processing confirmation for group: %d", groupId);
	}

	/**
	 * Handles a JSON event.
	 *
	 * @param groupId the ID of the VK group
	 * @param secret  the secret key for verification
	 * @param json    the JSON object
	 * @throws IllegalArgumentException if groupId or json is null
	 */
	@Override
	public void jsonEvent(Integer groupId, String secret, JsonObject json) {
		Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		Preconditions.checkNotNull(json, "JSON cannot be null");
		LOGGER.atFine().log("Processing JSON event for group: %d", groupId);
		try {
			new VKJsonEvent(json, groupId).callEvent();
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to process jsonEvent event");
			throw e;
		}
	}
	// endregion
}