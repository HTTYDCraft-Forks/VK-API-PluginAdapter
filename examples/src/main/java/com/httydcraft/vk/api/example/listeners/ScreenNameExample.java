package com.httydcraft.vk.api.example.listeners;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.vk.bukkit.BukkitVkApiPlugin;
import com.httydcraft.vk.api.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.utils.DomainResolvedType;
import com.vk.api.sdk.objects.utils.responses.ResolveScreenNameResponse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Random;

/**
 * Listener for handling VK message events to resolve screen names.
 */
public class ScreenNameExample implements Listener {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private static final VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider().getVkApiClient();
	private static final GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider().getActor();
	private static final Random RANDOM = new Random();
	private static final String COMMAND = "What id: ";
	// endregion

	// region Event Handlers
	/**
	 * Handles VK message events to resolve screen names and return IDs.
	 *
	 * @param event the VK message event
	 * @throws IllegalArgumentException if event is null
	 */
	@EventHandler
	public void onMessage(VKMessageEvent event) {
		Preconditions.checkNotNull(event, "Event cannot be null");
		String text = event.getMessage().getText();
		if (!text.startsWith(COMMAND) || text.length() <= COMMAND.length()) {
			LOGGER.atFine().log("Message does not match command '%s' or is too short", COMMAND);
			return;
		}

		String screenName = text.substring(COMMAND.length());
		try {
			ResolveScreenNameResponse response = CLIENT.utils().resolveScreenName(ACTOR, screenName).execute();
			Integer id = response.getType() == DomainResolvedType.GROUP ? response.getGroupId() : response.getObjectId();
			CLIENT.messages()
					.send(ACTOR)
					.randomId(RANDOM.nextInt())
					.peerId(event.getPeer())
					.message("ID: " + id)
					.execute();
			LOGGER.atFine().log("Resolved screen name '%s' to ID: %d", screenName, id);
		} catch (ApiException | ClientException e) {
			try {
				CLIENT.messages()
						.send(ACTOR)
						.randomId(RANDOM.nextInt())
						.peerId(event.getPeer())
						.message("ERROR!")
						.execute();
				LOGGER.atSevere().withCause(e).log("Failed to resolve screen name: %s", screenName);
			} catch (ApiException | ClientException sendEx) {
				LOGGER.atSevere().withCause(sendEx).log("Failed to send error message");
			}
		}
	}
	// endregion
}