package com.httydcraft.vk.api.core.api.providers;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.parsers.LongpoolEventParser;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;

/**
 * Abstract provider for VK API integration.
 */
public abstract class AbstractVkApiProvider implements VkApiProvider {
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    protected final GroupActor groupActor;
    protected final VkApiClient vkApiClient;
    protected final LongpoolEventParser longpoolEventParser;

    /**
     * Creates a new instance of AbstractVkApiProvider.
     *
     * @param pluginConfig         the plugin configuration
     * @param longpoolEventParser  the longpool event parser
     */
    public AbstractVkApiProvider(PluginConfig pluginConfig, LongpoolEventParser longpoolEventParser) {
        Preconditions.checkNotNull(pluginConfig, "pluginConfig cannot be null");
        Preconditions.checkNotNull(longpoolEventParser, "longpoolEventParser cannot be null");
        this.vkApiClient = new VkApiClient(HttpTransportClient.getInstance());

        int groupId =
                pluginConfig.getGroupId() < 0 ?
                        getGroupId(pluginConfig.getGroupToken()) :
                        pluginConfig.getGroupId();
        this.groupActor = new GroupActor(groupId, pluginConfig.getGroupToken());
        this.longpoolEventParser = longpoolEventParser;
        logger.atInfo().log("AbstractVkApiProvider initialized for groupId=%d", groupId);
    }

    @Override
    public GroupActor getActor() {
        return groupActor;
    }

    @Override
    public VkApiClient getVkApiClient() {
        return vkApiClient;
    }

    @Override
    public LongpoolEventParser getLongpoolParser() {
        return longpoolEventParser;
    }

    private int getGroupId(String accessToken) {
        Preconditions.checkNotNull(accessToken, "accessToken cannot be null");
        try {
            return vkApiClient.groups().getByIdLegacy(new GroupActor(null, accessToken)).execute().get(0).getId();
        } catch(ApiException | ClientException e) {
            logger.atSevere().withCause(e).log("Failed to get group id from access token");
        }
        throw new UnsupportedOperationException("Cannot get group id from invalid access token");
    }
}
