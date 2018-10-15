package org.misha.threads.buffer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BufferConfig.class})
public class BoundedBufferTest {
    @Autowired
    private BoundedBuffer<Integer> boundedBuffer;

    @Autowired
    @Qualifier("producers")
    private ExecutorService producers;

    @Autowired
    @Qualifier("consumers")
    private ExecutorService consumers;

    @Test
    public void put() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; ++i) {
            ProducerTask producerTask = new ProducerTask(new Producer(boundedBuffer));
            ConsumerTask consumerTask = new ConsumerTask(new Consumer(boundedBuffer));
            Future<Integer> producerFuture = producers.submit(producerTask);
            Future<Integer> consumerFuture = consumers.submit(consumerTask);
            assertEquals(producerFuture.get(), consumerFuture.get());
        }
    }

    private static class Producer {
        private final BoundedBuffer<Integer> buffer;

        private Producer(BoundedBuffer<Integer> buffer) {
            this.buffer = buffer;
        }

        private Integer produce() {
            try {
                int v = new Random().nextInt();
                buffer.put(v);
                System.err.println("produced " + v);
                return v;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }

    private static class Consumer {
        private final BoundedBuffer<Integer> buffer;

        private Consumer(BoundedBuffer<Integer> buffer) {
            this.buffer = buffer;
        }

        private Integer consume() {
            try {
                Integer taken = buffer.take();
                System.err.println("consumed " + taken);
                return taken;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }

    private static class ProducerTask implements Callable<Integer> {
        private final Producer producer;

        private ProducerTask(Producer producer) {
            this.producer = producer;
        }

        @Override
        public Integer call() {
            return producer.produce();
        }
    }

    private static class ConsumerTask implements Callable<Integer> {
        private final Consumer consumer;

        private ConsumerTask(Consumer consumer) {
            this.consumer = consumer;
        }

        @Override
        public Integer call() {
            return consumer.consume();
        }
    }
}