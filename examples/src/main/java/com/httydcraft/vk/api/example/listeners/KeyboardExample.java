package com.httydcraft.vk.api.example.listeners;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.vk.bukkit.BukkitVkApiPlugin;
import com.httydcraft.vk.api.bukkit.events.VKMessageEvent;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;
import com.vk.api.sdk.objects.messages.KeyboardButtonColor;
import com.vk.api.sdk.objects.messages.TemplateActionTypeNames;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Listener for handling VK message events to demonstrate keyboard functionality.
 */
public class KeyboardExample implements Listener {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private static final VkApiClient CLIENT = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider().getVkApiClient();
	private static final GroupActor ACTOR = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class).getVkApiProvider().getActor();
	private static final Random RANDOM = new Random();
	// endregion

	// region Event Handlers
	/**
	 * Handles VK message events to send a keyboard when a specific command is received.
	 *
	 * @param event the VK message event
	 * @throws IllegalArgumentException if event is null
	 */
	@EventHandler
	public void onMessage(VKMessageEvent event) {
		Preconditions.checkNotNull(event, "Event cannot be null");
		if (!event.getMessage().getText().startsWith("Keyboard")) {
			LOGGER.atFine().log("Message does not start with 'Keyboard', ignoring");
			return;
		}
		try {
			CLIENT.messages()
					.send(ACTOR)
					.randomId(RANDOM.nextInt())
					.keyboard(createKeyboard())
					.peerId(event.getPeer())
					.message("Message Text")
					.execute();
			LOGGER.atFine().log("Sent keyboard message to peer: %d", event.getPeer());
		} catch (ApiException | ClientException e) {
			LOGGER.atSevere().withCause(e).log("Failed to send keyboard message");
		}
	}
	// endregion

	// region Public Methods
	/**
	 * Creates a keyboard with predefined buttons.
	 *
	 * @return the configured keyboard
	 */
	public Keyboard createKeyboard() {
		Keyboard keyboard = new Keyboard();
		List<List<KeyboardButton>> allKey = new ArrayList<>();

		// First line
		List<KeyboardButton> line1 = new ArrayList<>();
		KeyboardButton button = new KeyboardButton();
		KeyboardButtonAction action = new KeyboardButtonAction();
		action.setLabel("Text on button");
		action.setType(TemplateActionTypeNames.TEXT);
		button.setAction(action);
		button.setColor(KeyboardButtonColor.DEFAULT);
		line1.add(button);

		// Second line
		List<KeyboardButton> line2 = new ArrayList<>();
		line2.add(new KeyboardButton()
				.setAction(new KeyboardButtonAction()
						.setLabel("/help")
						.setType(TemplateActionTypeNames.TEXT))
				.setColor(KeyboardButtonColor.NEGATIVE));

		allKey.add(line1);
		allKey.add(line2);
		keyboard.setButtons(allKey);

		LOGGER.atFine().log("Created keyboard with %d lines", allKey.size());
		return keyboard;
	}
	// endregion
}