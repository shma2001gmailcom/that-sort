package org.misha.luxoft;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LamportTest {
    private static final int THREAD_COUNT = 3;

    @Test
    public void testSharedCounter() throws InterruptedException {
        final Lamport lock = new Lamport(THREAD_COUNT);
        final List<Integer> value = new ArrayList<>();
        value.add(0);
        final List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; ++i) threads.add(new Thread(new Lamport.MyRunnable(i, lock, value)));
        threads.forEach(Thread::start);
        Thread.sleep(5000);
        System.err.println("result: " + value.get(0));
    }
}
