package org.misha.threads.semaphore.poducer;

/**
 * Author: mshevelin
 * Date: 2/3/15
 * Time: 10:12 AM
 */
public abstract class Consumer<T> implements Runnable {
    private final BlockingQueueSemaphore<T> queue;

    protected Consumer(final BlockingQueueSemaphore<T> queue) {
        this.queue = queue;
    }

    public abstract void consume(final T t);

    @Override
    public void run() {
        for (int i = 1; i < 20; ++i) {
            try {
                final T removed = queue.remove();
                consume(removed);
                System.err.println("size=" + queue.size() + " removed=" + removed);
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

