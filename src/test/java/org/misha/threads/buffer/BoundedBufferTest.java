package org.misha.threads.buffer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ExecutorService executor;

    @Test
    public void put() throws ExecutionException, InterruptedException {
        for (int i = 0; i < 100; ++i) {
            ProducerTask<Integer> producerTask = new ProducerTask<>(new Producer<Integer>(boundedBuffer) {
                @Override
                protected Integer produceNext() {
                    return new Random().nextInt(200);
                }
            });
            ConsumerTask<Integer> consumerTask = new ConsumerTask<>(new Consumer<>(boundedBuffer));
            Future<Integer> consumerFuture = executor.submit(consumerTask);
            Future<Integer> producerFuture = executor.submit(producerTask);
            assertEquals(producerFuture.get(), consumerFuture.get());
        }
    }

    private static abstract class Producer<V> {
        private final BoundedBuffer<V> buffer;

        private Producer(BoundedBuffer<V> buffer) {
            this.buffer = buffer;
        }

        private V produce() {
            try {
                V v = produceNext();
                buffer.put(v);
                System.err.println("produced " + v);
                return v;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }

        protected abstract V produceNext();
    }

    private static class Consumer<V>{
        private final BoundedBuffer<V> buffer;

        private Consumer(BoundedBuffer<V> buffer) {
            this.buffer = buffer;
        }

        private V consume() {
            try {
                V taken = buffer.take();
                System.err.println("consumed " + taken);
                return taken;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return null;
        }
    }

    private static class ProducerTask<V> implements Callable<V> {
        private final Producer<V> producer;

        private ProducerTask(Producer<V> producer) {
            this.producer = producer;
        }

        @Override
        public V call() {
            return producer.produce();
        }
    }

    private static class ConsumerTask<V> implements Callable<V> {
        private final Consumer<V> consumer;

        private ConsumerTask(Consumer<V> consumer) {
            this.consumer = consumer;
        }

        @Override
        public V call() {
            return consumer.consume();
        }
    }
}