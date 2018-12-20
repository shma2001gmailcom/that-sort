package org.misha.threads.waitnotify;

/**
 * author: misha
 * date: 5/13/17
 * time: 3:31 PM
 */
class WaitBase {
    private final Object mutex = new Object();
    private volatile boolean wasSignal = false;

    void doWait() {
        synchronized (mutex) {
            while (!wasSignal) {
                try {
                    mutex.wait(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            wasSignal = false;
        }
    }

    void doNotify() {
        synchronized (mutex) {
            wasSignal = true;
            mutex.notifyAll();
        }
    }
}

