package org.misha.threads.buffer;

abstract class BaseBoundedBuffer<V> {
    private final V[] buf;
    private int tail;
    private int head;
    private int count;

    BaseBoundedBuffer(int capacity) {
        this.buf = (V[]) new Object[capacity];
    }

    synchronized final void doPut(V v) {
        buf[tail] = v;
        if (++tail == buf.length)
            tail = 0;
        ++count;
    }

    synchronized final V doTake() {
        V v = buf[head];
        buf[head] = null;
        if (++head == buf.length)
            head = 0;
        --count;
        return v;
    }

    synchronized final boolean isFull() {
        return count == buf.length;
    }

    synchronized final boolean isEmpty() {
        return count == 0;
    }
}
