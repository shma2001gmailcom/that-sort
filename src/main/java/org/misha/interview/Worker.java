package org.misha.interview;

/**
 * author: misha
 * date: 3/16/16
 * time: 11:30 AM
 */
public class Worker implements Runnable {
    Counter counter;

    public Worker(Counter c) {
        counter = c;
    }

    @Override
    public void run() {
        counter.nextValue();
    }
}
