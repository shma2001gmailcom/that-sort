package org.misha.threads.waitnotify.ticktack.bool;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class TickTack {
    static final int LIMIT = 10;
    private static final Logger LOG = Logger.getLogger(TickTack.class);
    private final TickTacker tick;
    private final TickTacker tack;
    private final CountDownLatch latch;
    private final List<String> history;

    private TickTack() {
        final AtomicBoolean tickTackTracer = new AtomicBoolean(true);
        history = new CopyOnWriteArrayList<>();
        latch = new CountDownLatch(2);
        tick = new TickTacker(latch, tickTackTracer, history, false, "tick");
        tack = new TickTacker(latch, tickTackTracer, history, true, "tack");
    }

    public static void main(String... a) {
        new TickTack().tickTack();
    }

    private void tickTack() {
        new Thread(tick).start();
        new Thread(tack).start();
        startLatch();
        LOG.debug(history);
    }

    private void startLatch() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
