package com.httydcraft.vk.api.core.api.config;

import com.httydcraft.vk.api.core.proxy.DefaultSystemProxyApplier;

/**
 * Configuration interface for VK API plugin settings.
 */
public interface PluginConfig {
    // region Constants
    /** Key for settings configuration section. */
    String SETTINGS_KEY = "settings";
    /** Key for group information configuration section. */
    String GROUP_INFO_KEY = "groupInfo";
    /** Key for proxy configuration section. */
    String PROXY_KEY = "proxy";
    /** Key for scheduler delay configuration. */
    String SCHEDULER_DELAY_KEY = "delay";
    /** Key for logging enabled configuration. */
    String LOGGING_ENABLED_KEY = "logging";
    /** Key for group ID configuration. */
    String GROUP_ID_KEY = "groupID";
    /** Key for group token configuration. */
    String GROUP_TOKEN_KEY = "groupToken";
    /** Key for proxy type configuration. */
    String PROXY_TYPE_KEY = "type";
    /** Key for proxy host configuration. */
    String PROXY_HOST_KEY = "host";
    /** Key for proxy port configuration. */
    String PROXY_PORT_KEY = "port";
    /** Default proxy type value. */
    String DEFAULT_PORT_TYPE = DefaultSystemProxyApplier.NONE.name();
    /** Default group ID value. */
    int DEFAULT_GROUP_ID = -1;
    // endregion

    // region Methods
    /**
     * Retrieves the delay for the longpoll scheduler.
     *
     * @return the scheduler delay in milliseconds
     */
    int getLongpoolSchedulerDelay();

    /**
     * Retrieves the VK group ID.
     *
     * @return the group ID
     */
    int getGroupId();

    /**
     * Retrieves the VK group token.
     *
     * @return the group token
     */
    String getGroupToken();

    /**
     * Checks if logging is enabled.
     *
     * @return true if logging is enabled, false otherwise
     */
    boolean isLoggingEnabled();

    /**
     * Retrieves the proxy type.
     *
     * @return the proxy type
     */
    String getProxyType();

    /**
     * Retrieves the proxy host.
     *
     * @return the proxy host
     */
    String getProxyHost();

    /**
     * Retrieves the proxy port.
     *
     * @return the proxy port
     */
    int getProxyPort();
    // endregion
}