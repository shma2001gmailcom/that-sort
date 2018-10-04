package org.misha.threads.lock;

/**
 * author: misha
 * date: 5/14/17
 * time: 11:03 AM
 */
class Counter {
    private final Lock lock = new Lock();
    private int i = 0;

    int inc() {
        lock.lock();
        int newValue = ++i;
        lock.unlock();
        return newValue;
    }
}
