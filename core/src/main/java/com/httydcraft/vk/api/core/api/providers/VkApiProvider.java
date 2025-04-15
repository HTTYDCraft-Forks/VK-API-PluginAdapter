package com.httydcraft.vk.api.core.api.providers;

import java.util.Collections;

import com.google.common.flogger.GoogleLogger;
import com.google.common.base.Preconditions;
import com.httydcraft.vk.api.core.api.parsers.LongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;

/**
 * Interface for VK API providers.
 */
public interface VkApiProvider {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Returns the VK API client.
     */
    VkApiClient getVkApiClient();

    /**
     * Returns the group actor.
     */
    GroupActor getActor();

    /**
     * Returns the longpool event parser.
     */
    LongpoolEventParser getLongpoolParser();

    /**
     * Deletes a message.
     * 
     * @param message the message to delete
     * @return true if the message was deleted successfully, false otherwise
     */
    @Deprecated
    default boolean deleteMessage(Message message){
        Preconditions.checkNotNull(message, "Message cannot be null");
        try {
            getVkApiClient().messages().delete(getActor()).messageIds(Collections.singletonList(message.getId())).deleteForAll(true)
                    .execute();
            logger.atInfo().log("Message %s deleted successfully", message.getId());
            return true;
        } catch (ApiException | ClientException e) {
            logger.atSevere().withCause(e).log("Failed to delete message %s", message.getId());
        }
        return false;
    }
}
