package com.httydcraft.vk.api.core.api.scheduler;

import java.util.concurrent.ExecutionException;

import com.google.common.flogger.GoogleLogger;

/**
 * Abstract runnable that supports cancellation and scheduler integration.
 * 
 * @author vladimir-bukhtoyarov source from
 *         <a href="https://gist.github.com/vladimir-bukhtoyarov/b71fe668ce53e1e81856ccc7e99a3150">...</a>
 */
public abstract class CancellableRunnable implements Runnable {
    private Scheduler scheduler;
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();

    /**
     * Main run method to be implemented by subclasses.
     */
    @Override
    public abstract void run();

    /**
     * Cancels the scheduled task if possible.
     */
    public void cancel() {
        try {
            if (scheduler != null) {
                scheduler.cancelAndBeSureOfTermination(true);
                logger.atInfo().log("Task cancelled");
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.atSevere().withCause(e).log("Error during cancellation");
        }
    }

    /**
     * Sets the scheduler for this runnable. Null is ignored for safety.
     * @param scheduler Scheduler instance
     */
    protected void setScheduler(Scheduler scheduler) {
        if (scheduler == null) {
            logger.atWarning().log("Attempted to set null scheduler");
            return;
        }
        this.scheduler = scheduler;
    }
}
