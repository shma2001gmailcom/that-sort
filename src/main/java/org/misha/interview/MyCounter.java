package org.misha.interview;

import java.util.concurrent.atomic.AtomicLong;

/**
 * author: misha
 * date: 7/7/15 10:58 PM.
 */
class MyCounter implements Counter {
    private final AtomicLong value;

    MyCounter(final AtomicLong value) {
        this.value = value;
    }

    @Override
    public long currentValue() {
        return value.get();
    }

    @Override
    public long nextValue() {
        return value.incrementAndGet();
    }
}
