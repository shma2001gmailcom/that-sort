package org.misha.threads.semaphore.poducer;

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
    private final List<T> queue = new ArrayList<>();
    private final Semaphore item;
    private final Semaphore availableItems;

    private BlockingQueueSemaphore(final int size) {
        capacity = size;
        item = new Semaphore(1);
        availableItems = new Semaphore(capacity);
    }

    public static <S> BlockingQueueSemaphore<S> instance(final int capacity) {
        return new BlockingQueueSemaphore<>(capacity);
    }

    public void add(final T object) throws InterruptedException {
        try {
            availableItems.acquire();
            if (size() == capacity - 1) {
                synchronized (this) {
                    while (size() == capacity - 1) {
                        wait();
                    }
                    queue.add(object);
                    notifyAll();
                }
            }
        } finally {
            availableItems.release();
        }
    }

    public synchronized int size() {
        return queue.size();
    }

    T remove() throws InterruptedException {
        T result = null;
        try {
            item.acquire();
            if (queue.isEmpty()) {
                synchronized (this) {
                    while (queue.isEmpty()) {
                        wait();
                    }
                    result = queue.remove(0);
                    notifyAll();
                }
            }
        } finally {
            item.release();
        }
        return result;
    }
}
