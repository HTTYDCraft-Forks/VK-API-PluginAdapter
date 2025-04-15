package com.httydcraft.vk.api.core.api.scheduler;

import com.google.common.base.Preconditions;
import com.google.common.flogger.GoogleLogger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Scheduler for running cancellable tasks at a fixed rate.
 * Source: https://gist.github.com/vladimir-bukhtoyarov/b71fe668ce53e1e81856ccc7e99a3150
 */
public class Scheduler {
    // #region Fields
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);
    private final ScheduledFuture<?> targetFuture;
    private final CancellableCommand runnable;
    private static final GoogleLogger logger = GoogleLogger.forEnclosingClass();
    // #endregion

    // #region Scheduling
    /**
     * Schedules a cancellable runnable at a fixed rate.
     * @param runnable the task to run
     * @param initialDelay initial delay
     * @param period period between executions
     * @param unit time unit
     * @return Scheduler instance
     */
    public static Scheduler scheduleAtFixedRate(CancellableRunnable runnable, long initialDelay, long period,
                                                TimeUnit unit) {
        Preconditions.checkNotNull(runnable, "Runnable cannot be null");
        Preconditions.checkArgument(period > 0, "Period must be positive");
        CancellableCommand cancellableCommand = new CancellableCommand(runnable);
        ScheduledFuture<?> future = SCHEDULER.scheduleAtFixedRate(cancellableCommand, initialDelay, period, unit);
        Scheduler scheduler = new Scheduler(future, cancellableCommand);
        runnable.setScheduler(scheduler);
        logger.atInfo().log("Scheduled task with period %d %s", period, unit);
        return scheduler;
    }
    // #endregion

    // #region Constructor
    private Scheduler(ScheduledFuture<?> targetFuture, CancellableCommand runnable) {
        this.targetFuture = Preconditions.checkNotNull(targetFuture, "targetFuture cannot be null");
        this.runnable = Preconditions.checkNotNull(runnable, "runnable cannot be null");
    }
    // #endregion

    // #region Cancellation
    /**
     * Cancels the scheduled task and ensures termination.
     * @param mayInterruptIfRunning whether to interrupt if running
     * @throws InterruptedException if interrupted
     * @throws ExecutionException if execution fails
     */
    public void cancelAndBeSureOfTermination(boolean mayInterruptIfRunning)
            throws InterruptedException, ExecutionException {
        try {
            targetFuture.cancel(mayInterruptIfRunning);
            logger.atInfo().log("Task cancelled");
        } finally {
            runnable.cancel();
        }
    }
    // #endregion

    // #region Inner Classes
    private static class CancellableCommand implements Runnable {
        private static final int NOT_EXECUTING = 0;
        private static final int IN_PROGRESS = 1;
        private static final int CANCELLED_WITHOUT_OBSTRUCTION = 2;
        private static final int CANCELLED_IN_MIDDLE_OF_PROGRESS = 3;

        private final AtomicInteger state = new AtomicInteger(NOT_EXECUTING);
        private final AtomicReference<Thread> executionThread = new AtomicReference<>();
        private final CompletableFuture<Void> cancellationFuture = new CompletableFuture<>();
        private final Runnable target;

        private CancellableCommand(Runnable target) {
            this.target = Preconditions.checkNotNull(target, "target cannot be null");
        }

        /**
         * Cancels the command.
         * @throws ExecutionException if execution fails
         * @throws InterruptedException if interrupted
         */
        public void cancel() throws ExecutionException, InterruptedException {
            state.set(CANCELLED_WITHOUT_OBSTRUCTION);
            cancellationFuture.complete(null);
            GoogleLogger.forEnclosingClass().atInfo().log("CancellableCommand cancelled");
        }

        @Override
        public void run() {
            if (!state.compareAndSet(NOT_EXECUTING, IN_PROGRESS)) return;
            executionThread.set(Thread.currentThread());
            try {
                target.run();
            } finally {
                state.set(NOT_EXECUTING);
                executionThread.set(null);
            }
        }
    }
    // #endregion
}
