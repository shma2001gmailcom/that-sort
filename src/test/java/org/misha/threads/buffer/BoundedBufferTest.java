package org.misha.threads.buffer;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BoundedBufferTest {

    @Test
    public void put() throws ExecutionException, InterruptedException {
        ExecutorService producers = Executors.newFixedThreadPool(10);
        ExecutorService consumers = Executors.newFixedThreadPool(3);
        BoundedBuffer<Integer> buffer = new BoundedBuffer<>(3);
        for (int i = 0; i < 10000; ++i) {
            ProducerTask producerTask = new ProducerTask(new Producer(buffer));
            ConsumerTask consumerTask = new ConsumerTask(new Consumer(buffer));
            producers.submit(producerTask).get();
            consumers.submit(consumerTask).get();
        }

    }

    private static class Producer {
        private final BoundedBuffer<Integer> buffer;

        private Producer(BoundedBuffer<Integer> buffer) {
            this.buffer = buffer;
        }

        public void produce() {
            try {
                int v = new Random().nextInt();
                buffer.put(v);
                System.err.println("produced "+ v);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class  Consumer {
        private final BoundedBuffer<Integer> buffer;

        private Consumer(BoundedBuffer<Integer> buffer) {
            this.buffer = buffer;
        }

        public void consume() {
            try {
                System.err.println("consumed " + buffer.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static class ProducerTask implements Callable<Void> {
        private final Producer producer;

        private ProducerTask(Producer producer) {
            this.producer = producer;
        }

        @Override
        public Void call() {
            producer.produce();
            return null;
        }
    }

    private static class ConsumerTask implements Callable<Void> {
        private final Consumer consumer;

        private ConsumerTask(Consumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public Void call() {
            consumer.consume();
            return null;
        }
    }
}