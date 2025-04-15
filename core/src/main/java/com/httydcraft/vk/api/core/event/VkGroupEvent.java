package com.httydcraft.vk.api.core.event;

import com.google.common.flogger.GoogleLogger;

/**
 * Represents a VK group event.
 */
public interface VkGroupEvent {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();
    /**
     * @return Group id where event happened
     */
    int groupId();
}
