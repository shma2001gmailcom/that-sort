package org.misha.threads.latch;

import java.util.concurrent.CountDownLatch;

/**
 * author: misha
 * date: 11/27/16
 * time: 5:57 PM
 */
class LogWriter implements Runnable {
    private final CountDownLatch latch;

    private LogWriter(final CountDownLatch latch) {
        this.latch = latch;
    }

    static LogWriter createLogWriter(final CountDownLatch latch) {
        return new LogWriter(latch);
    }

    private static void writeLogs() {
        //make something meaning here
        Csv.log.error("writing logs");
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Csv.log.info("latch is open");
        writeLogs();
    }
}
