package org.misha.sort.that;

import java.util.concurrent.CountDownLatch;

/**
 * author: misha
 * date: 1/2/17
 * time: 2:45 PM
 */
class Actor implements Runnable {
    private final CountDownLatch latch;

    private Actor(final CountDownLatch latch) {
        this.latch = latch;
    }

    static Actor getInstance(final CountDownLatch latch) {
        return new Actor(latch);
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
