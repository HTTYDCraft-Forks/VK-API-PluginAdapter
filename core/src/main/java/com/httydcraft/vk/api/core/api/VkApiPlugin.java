package com.httydcraft.vk.api.core.api;

import com.httydcraft.vk.api.core.api.config.PluginConfig;
import com.httydcraft.vk.api.core.api.providers.VkApiProvider;
import com.httydcraft.vk.api.core.proxy.DefaultSystemProxyApplier;
import com.httydcraft.vk.api.core.proxy.ProxyApplier;

public interface VkApiPlugin {
    VkApiProvider getVkApiProvider();

    PluginConfig getPluginConfig();

    void callEvent(Object event);

    default ProxyApplier getProxyApplier(String rawType) {
        return DefaultSystemProxyApplier.valueOf(rawType);
    }
}
