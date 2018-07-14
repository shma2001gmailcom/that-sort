package org.misha.threads.semaphore.ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 10:49 AM
 */

public final class BlockingQueueSemaphore<T> {
    private final int capacity;
    private final List<T> queue = new ArrayList<T>();
    private final Semaphore item;
    private final Semaphore availableItems;

    private BlockingQueueSemaphore(final int size) {
        capacity = size;
        item = new Semaphore(1);
        availableItems = new Semaphore(capacity);
    }

    public static <S> BlockingQueueSemaphore<S> instance(final int capacity) {
        return new BlockingQueueSemaphore<S>(capacity);
    }

    public void add(final T object) throws InterruptedException {
        try {
            availableItems.acquire();
            synchronized (this) {
                while (size() == capacity - 1) {
                    wait();
                }
                queue.add(object);
                notifyAll();
            }
        } finally {
            availableItems.release();
        }
    }

    public synchronized int size() {
        return queue.size();
    }

    public T remove() throws InterruptedException {
        T result;
        try {
            item.acquire();
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait();
                }
                result = queue.remove(0);
                notifyAll();
            }
        } finally {
            item.release();
        }
        return result;
    }
}
