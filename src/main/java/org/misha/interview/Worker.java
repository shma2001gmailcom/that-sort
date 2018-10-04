package org.misha.interview;

/**
 * author: misha
 * date: 3/16/16
 * time: 11:30 AM
 */
class Worker implements Runnable {
    private final Counter counter;

    Worker(Counter c) {
        counter = c;
    }

    @Override
    public void run() {
        counter.nextValue();
    }
}
