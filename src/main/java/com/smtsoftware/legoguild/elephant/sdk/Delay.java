package com.smtsoftware.legoguild.elephant.sdk;

/**
 * Simple collection of time delay routines that are non interruptable.
 * @author andy
 */
public class Delay {

    public static void sDelay(long period) {
        if (period <= 0) return;
        Logger.print("sDelay()");
        long end = System.nanoTime() + period/1000;
        waitForMillis(period * 1000);
        // To improve accuracy for small time periods we use a spin loop.
        // Note that we will still have jitter (due to the scheduler, but
        // this is probably better than nothing).
        while (System.nanoTime() < end)
        {
            // just spin
        }
    }

    /**
     * Wait for the specified number of milliseconds.
     * Delays the current thread for the specified period of time. Can not
     * be interrupted (but it does preserve the interrupted state).
     * @param period time to wait in ms
     */
    private static void msDelay(long period)
    {
        if (period <= 0) return;
        Logger.print("msDelay()");
        waitForMillis(period);
    }

    /**
     * Wait for the specified number of microseconds.
     * Delays the current thread for the specified period of time. Can not
     * be interrupted.
     * @param period time to wait in us
     */
    public static void usDelay(long period)
    {
        if (period <= 0) return;
        Logger.print("usDelay()");
        long end = System.nanoTime() + period*1000;
        waitForMillis(period/1000);
        // To improve accuracy for small time periods we use a spin loop.
        // Note that we will still have jitter (due to the scheduler, but
        // this is probably better than nothing).
        while (System.nanoTime() < end)
        {
            // just spin
        }
    }

    /**
     * Wait for the specified number of nanoseconds.
     * Delays the current thread for the specified period of time. Can not
     * be interrupted.
     * @param period time to wait in ns
     */
    public static void nsDelay(long period)
    {
        Logger.print("nsDelay()");
        long end = System.nanoTime() + period;
        waitForMillis(period/1000000);
        // To improve accuracy for small time periods we use a spin loop.
        // Note that we will still have jitter (due to the scheduler, but
        // this is probably better than nothing).
        while (System.nanoTime() < end)
        {
        	// just spin
        }
    }

    private static void waitForMillis(long period) {
        long end = System.currentTimeMillis() + period;
        boolean interrupted = false;
        do {
            try {
                Thread.sleep(period);
            } catch (InterruptedException ie)
            {
                interrupted = true;
            }
            period = end - System.currentTimeMillis();
        } while (period > 0);
        if (interrupted)
            Thread.currentThread().interrupt();
    }
}