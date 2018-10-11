package org.misha.threads.buffer;

/**
 * as in "Java Concurrency In Practice" by
 * Brian Göetz
 * Tim Peierls
 * Joshua Bloch
 * Joseph Bowbeer
 * David Holmes
 * Doug Lea
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    // CONDITION PREDICATE: not-full (!isFull())
    // CONDITION PREDICATE: not-empty (!isEmpty())
    public BoundedBuffer(int size) {
        super(size);
    }

    // BLOCKS-UNTIL: not-full
    public synchronized void put(V v) throws InterruptedException {
        while (isFull())
            wait(1000);
        doPut(v);
        notifyAll();
    }

    // BLOCKS-UNTIL: not-empty
    public synchronized V take() throws InterruptedException {
        while (isEmpty())
            wait(1000);
        V v = doTake();
        notifyAll();
        return v;
    }
}