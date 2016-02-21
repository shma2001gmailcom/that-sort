package org.misha.semaphore.wait_notify;

import java.util.LinkedList;
import java.util.List;

/**
 * author: misha
 * date: 1/31/15 2:46 PM.
 */
public class BlockingQueueWaitNotify<T> {

    private final List<T> queue = new LinkedList<T>();
    private int limit = 10;

    public BlockingQueueWaitNotify(final int limit) {
        this.limit = limit;
    }

    public static void main(final String[] args) {
        final BlockingQueueWaitNotify<Integer> queue = new BlockingQueueWaitNotify<Integer>(50);
        final Runnable thread1 = new Runnable() {
            @Override
            public void run() {
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
                        System.exit(0);
                    }
                }
            }
        };
        final Runnable thread2 = new Runnable() {

            @Override
            public void run() {
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
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        new Thread(thread1).start();
        new Thread(thread2).start();
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized void add(final T item) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(item);
        notifyAll();
    }

    public synchronized T remove() throws InterruptedException {
        while (queue.isEmpty()) {
            if (queue.isEmpty()) {
                wait();
            }
        }
        final T t = queue.remove(0);
        notifyAll();
        return t;
    }
}
