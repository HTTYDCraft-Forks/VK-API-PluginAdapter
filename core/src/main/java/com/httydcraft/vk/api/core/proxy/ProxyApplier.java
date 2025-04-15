package com.httydcraft.vk.api.core.proxy;

import com.google.common.flogger.GoogleLogger;

/**
 * Interface for applying proxy settings.
 */
public interface ProxyApplier {
    GoogleLogger logger = GoogleLogger.forEnclosingClass();
    /**
     * Applies the proxy settings.
     * @param host proxy host
     * @param port proxy port
     */
    void apply(String host, int port);
}
