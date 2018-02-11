package org.misha.threads.lock;

/**
 * author: misha
 * date: 5/14/17
 * time: 11:52 AM
 */
class Lock {
    private boolean locked;

    synchronized void lock() {
        while (locked) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        locked = true;
    }

    synchronized void unlock() {
        locked = false;
        notify();
    }
}
