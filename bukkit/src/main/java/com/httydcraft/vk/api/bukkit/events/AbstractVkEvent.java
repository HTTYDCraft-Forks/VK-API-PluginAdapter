package com.httydcraft.vk.api.bukkit.events;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.bukkit.BukkitVkApiPlugin;
import com.httydcraft.vk.api.core.event.VkGroupEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Base class for VK-related events in a Bukkit environment, implementing the {@link VkGroupEvent} interface.
 * Provides common functionality for handling group-specific events.
 */
public abstract class AbstractVkEvent extends Event implements VkGroupEvent {
	// region Fields
	private static final GoogleLogger LOGGER = GoogleLogger.forEnclosingClass();
	private static final HandlerList HANDLER_LIST = new HandlerList();
	private static final BukkitVkApiPlugin PLUGIN = BukkitVkApiPlugin.getPlugin(BukkitVkApiPlugin.class);
	private final Integer groupId;
	// endregion

	// region Constructors
	/**
	 * Constructs an asynchronous VK event with the specified group ID.
	 *
	 * @param groupId the ID of the VK group associated with the event
	 * @throws IllegalArgumentException if groupId is null
	 */
	protected AbstractVkEvent(Integer groupId) {
		super(true);
		this.groupId = Preconditions.checkNotNull(groupId, "Group ID cannot be null");
		LOGGER.atFine().log("Initialized %s for group: %d", this.getClass().getSimpleName(), groupId);
	}
	// endregion

	// region Public Methods
	/**
	 * Returns the ID of the VK group associated with this event.
	 *
	 * @return the group ID
	 */
	@Override
	public int groupId() {
		return groupId;
	}

	/**
	 * Triggers the event by calling it through the plugin's event system.
	 */
	public void callEvent() {
		LOGGER.atFine().log("Calling event: %s", this.getClass().getSimpleName());
		try {
			PLUGIN.callEvent(this);
		} catch (Exception e) {
			LOGGER.atSevere().withCause(e).log("Failed to call event");
			throw e;
		}
	}

	/**
	 * Returns the handler list for this event.
	 *
	 * @return the handler list
	 */
	public static HandlerList getHandlerList() {
		return HANDLER_LIST;
	}

	/**
	 * Returns the handlers for this event instance.
	 *
	 * @return the handler list
	 */
	@Override
	public final HandlerList getHandlers() {
		return HANDLER_LIST;
	}
	// endregion
}