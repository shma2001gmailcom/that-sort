package org.misha.threads.semaphore;

import org.misha.threads.semaphore.ProducerConsumer.Consumer;
import org.misha.threads.semaphore.ProducerConsumer.Producer;

/**
 * Author: mshevelin
 * Date: 2/3/15
 * Time: 10:13 AM
 */

final class Linker<T> {
    private final Producer<T> producer;
    private final Consumer<T> consumer;

    Linker(final Producer<T> producer, final Consumer<T> consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    void link() {
        final Thread t1 = new Thread(producer);
        final Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
    }
}
