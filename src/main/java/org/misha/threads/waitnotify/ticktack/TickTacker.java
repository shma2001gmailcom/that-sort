package org.misha.threads.waitnotify.ticktack;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

class TickTacker implements Runnable {
    private final AtomicBoolean tracer;
    private final List<String> track;
    private final CountDownLatch latch;
    private final boolean ownValue;
    private final String tickOrTack;

    TickTacker(final CountDownLatch countDownLatch,
               final AtomicBoolean tickTackTracer,
               final List<String> history,
               final boolean value,
               final String message
    ) {
        tracer = tickTackTracer;
        ownValue = value;
        track = history;
        latch = countDownLatch;
        tickOrTack = message;
    }

    public void run() {
        while (track.size() < TickTack.LIMIT) {
            if (tracer.get() != ownValue) {
                synchronized (latch) {
                    tracer.set(ownValue);
                    track.add(tickOrTack);
                }
            }
        }
        latch.countDown();
    }
}
