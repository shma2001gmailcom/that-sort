package org.misha.threads.waitnotify;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * author: misha
 * date: 5/14/17
 * time: 10:31 AM
 */
public class Main {
    public static void main(String... args) {
        final CopyOnWriteArrayList<Integer> res = new CopyOnWriteArrayList<>();
        final Random random = new Random();
        final MyInteger u = new MyInteger();
        final AtomicBoolean done = new AtomicBoolean(false);
        final Runnable willWait = new WillWait(done, random, u, res);
        final Thread thread0 = new Thread(willWait);
        final Runnable willNotWait = new WillNotWait(done, u, res);
        final Thread thread1 = new Thread(willNotWait);
        thread0.start();
        thread1.start();
    }

    static class MyInteger {
        int i = 0;

        void inc() {
            ++i;
        }
    }

    private static class WillNotWait implements Runnable {
        private final AtomicBoolean done;
        private final MyInteger u;
        private final CopyOnWriteArrayList<Integer> res;

        private WillNotWait(final AtomicBoolean done, final MyInteger u, final CopyOnWriteArrayList<Integer> res) {
            this.done = done;
            this.u = u;
            this.res = res;
        }

        @Override
        public void run() {
            new WaitBase() {
                void y() {
                    final boolean b = !done.get();
                    while (b) {
                        if (u.i >= 1000000) {
                            done.compareAndSet(false, true);
                            System.err.println(res);
                            Thread.currentThread().interrupt();
                            return;
                        }
                        u.inc();
                        res.add(u.i);
                        System.err.println(u.i);
                    }
                }
            }.y();
        }
    }

    private static class WillWait implements Runnable {
        private final AtomicBoolean done;
        private final Random random;
        private final MyInteger u;
        private final CopyOnWriteArrayList<Integer> res;

        WillWait(final AtomicBoolean done,
                 final Random random,
                 final MyInteger u,
                 final CopyOnWriteArrayList<Integer> res
        ) {
            this.done = done;
            this.random = random;
            this.u = u;
            this.res = res;
        }

        @Override
        public void run() {
            new WaitBase() {
                void x() {
                    final boolean b = !done.get();
                    while (b) {
                        if (random.nextBoolean()) {
                            doWait();
                        }
                        if (u.i >= 1000000) {
                            done.compareAndSet(false, true);
                            Thread.currentThread().interrupt();
                            return;
                        }
                        u.inc();
                        res.add(u.i);
                        System.err.println(u.i + "                0");
                        doNotify();
                    }
                }
            }.x();
        }
    }
}
