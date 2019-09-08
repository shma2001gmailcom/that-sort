package org.misha.sort.that;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * author: misha
 * date: 9/15/14 10:45 PM.
 */
class UnitThread implements Runnable {
    private final int integer;
    private final CountDownLatch latch;
    private final List<Integer> list;

    UnitThread(final int i, final CountDownLatch cdl, final List<Integer> l) {
        integer = i;
        latch = cdl;
        list = l;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(160L * integer);
            System.err.print('.');
            list.add(integer);
            latch.countDown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
