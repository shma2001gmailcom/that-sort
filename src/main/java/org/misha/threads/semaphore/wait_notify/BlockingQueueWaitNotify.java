package org.misha.threads.semaphore.wait_notify;

import java.util.LinkedList;
import java.util.List;

/**
 * author: misha
 * date: 1/31/15 2:46 PM.
 */
public class BlockingQueueWaitNotify<T> {
    private final List<T> queue = new LinkedList<>();
    private int limit;

    private BlockingQueueWaitNotify(final int limit) {
        this.limit = limit;
    }

    public static void main(final String[] args) {
        final BlockingQueueWaitNotify<Integer> queue = new BlockingQueueWaitNotify<>(50);
        final Runnable thread1 = () -> {
            for (; ; ) {
                try {
                    queue.add(1);
                    System.err.println(queue.size() + " 1 added");
                    queue.add(3);
                    System.err.println(queue.size() + " 3 added");
                    if (queue.size() == 50) {
                        Thread.currentThread().interrupt();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.exit(0);
                }
            }
        };
        final Runnable thread2 = () -> {
            for (; ; ) {
                try {
                    queue.add(2);
                    System.err.println(queue.size() + " 2 added");
                    int i = queue.remove();
                    System.err.println(queue.size() + " " + i + " removed");
                    if (queue.size() == 50) {
                        Thread.currentThread().interrupt();
                    }
                    System.err.println(queue.size() + " 2 added");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
        };
        new Thread(thread1).start();
        new Thread(thread2).start();
    }

    public synchronized int size() {
        return queue.size();
    }

    public void add(final T item) throws InterruptedException {
        if (queue.size() == limit) {
            synchronized (this) {
                while (queue.size() == limit) {
                    wait();
                }
                notifyAll();
            }
        }
        queue.add(item);
    }

    private T remove() throws InterruptedException {
        if (queue.isEmpty()) {
            synchronized (this) {
                while (queue.isEmpty()) {
                    wait();
                }
                notifyAll();
            }
        }
        return queue.remove(0);
    }
}
