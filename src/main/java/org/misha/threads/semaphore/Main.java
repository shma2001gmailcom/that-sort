package org.misha.threads.semaphore;

import org.misha.threads.semaphore.ProducerConsumer.BlockingQueueSemaphore;
import org.misha.threads.semaphore.ProducerConsumer.Consumer;
import org.misha.threads.semaphore.ProducerConsumer.Producer;

import java.util.Random;

/**
 * Author: mshevelin
 * Date: 2/3/15
 * Time: 10:13 AM
 */

public final class Main {
    private static final int CAPACITY = 51;

    public static void main(final String... args) {
        final BlockingQueueSemaphore<Integer> queue = BlockingQueueSemaphore.instance(CAPACITY);
        final Producer<Integer> producer = new Producer<Integer>(queue) {

            public Integer produce() {
                final int result = new Random().nextInt(11);
                System.err.println("size=" + queue.size() + " " + "produced=" + result);
                return result;
            }
        };
        final Consumer<Integer> consumer = new Consumer<Integer>(queue) {

            @Override
            public void consume(final Integer integer) {
                System.err.println("size=" + queue.size() + " " + "consumed=" + integer);
            }
        };
        final org.misha.threads.semaphore.Linker<Integer> linker =
                new org.misha.threads.semaphore.Linker<Integer>(producer, consumer);
        linker.link();
    }
}
