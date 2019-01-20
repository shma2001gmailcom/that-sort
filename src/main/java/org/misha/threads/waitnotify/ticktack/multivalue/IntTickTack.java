package org.misha.threads.waitnotify.ticktack.multivalue;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class IntTickTack {
    private static final Logger LOG = Logger.getLogger(IntTickTack.class);
    private final int historyLimit;
    private final CountDownLatch latch;
    private final List<String> history;
    private final int tickTackersCount;
    private final List<IntTickTacker> tickTackers;
    private final AtomicInteger counter;

    private IntTickTack(final int historyLimit, int tickTackersNumber) {
        this.historyLimit = historyLimit;
        final AtomicInteger tickTackTracer = new AtomicInteger(0);
        history = new ArrayList<>();
        tickTackersCount = tickTackersNumber;
        latch = new CountDownLatch(tickTackersCount);
        tickTackers = new ArrayList<>();
        counter = new AtomicInteger(0);
        for (int ownValue = 0; ownValue < tickTackersCount; ownValue++) {
            tickTackers.add(new IntTickTacker(
                    latch,
                    tickTackTracer,
                    history,
                    ownValue,
                    tickTackersCount, historyLimit, counter));
        }
    }

    private void tickTack() {
        final IntTickTack tickTack = new IntTickTack(historyLimit, tickTackersCount);
        tickTackers.forEach(t -> new Thread(t).start());
        try {
            tickTack.latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        LOG.debug(tickTack.history);
    }

    public static void main(String... a) {
        new IntTickTack(13, 3).tickTack();
    }
}
