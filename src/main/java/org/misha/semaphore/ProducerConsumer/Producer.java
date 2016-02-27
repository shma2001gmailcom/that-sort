package org.misha.semaphore.ProducerConsumer;

/**
 * Author: mshevelin
 * Date: 2/3/15
 * Time: 10:12 AM
 */

public abstract class Producer<T> implements Runnable {
    private final BlockingQueueSemaphore<T> queue;

    public Producer(final BlockingQueueSemaphore<T> queue) {
        this.queue = queue;
    }

    public abstract T produce();

    @Override
    public void run() {
        for (int i = 1; i < 20; ++i) {
            try {
                final T toAdd = produce();
                queue.add(toAdd);
                System.err.println(toAdd + " added, size=" + queue.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

