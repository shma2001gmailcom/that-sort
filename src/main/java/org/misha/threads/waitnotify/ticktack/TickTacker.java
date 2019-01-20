package org.misha.threads.waitnotify.ticktack;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class TickTacker implements Runnable {
    private static final Logger LOG = Logger.getLogger(TickTacker.class);
    private final int historyLimit;
    private final AtomicInteger tracer;
    private final List<String> track;
    private final CountDownLatch latch;
    private final int ownValue;
    private final Modulo m;
    private final AtomicInteger counter;

    TickTacker(final CountDownLatch countDownLatch,
               final AtomicInteger tickTackTracer,
               final List<String> history,
               final int value,
               final int modulo,
               final int historyLimit,
               final AtomicInteger counter
    ) {
        tracer = tickTackTracer;
        ownValue = value;
        track = history;
        latch = countDownLatch;
        m = new Modulo(modulo);
        this.historyLimit = historyLimit;
        this.counter = counter;
    }

    public void run() {
        while (counter.getAndUpdate(this::incFunction) < historyLimit - 1) ;
        latch.countDown();
        if (latch.getCount() == 0) {
            LOG.debug(track);
        }
    }

    private int incFunction(int v) {
        synchronized (latch) {
            if (m.modulo(tracer.get()) == m.modulo(ownValue - 1)) {
                tracer.set(m.modulo(ownValue));
                track.add(String.valueOf(ownValue));
                counter.incrementAndGet();
                return v + 1;
            }
            return v;
        }
    }
}
