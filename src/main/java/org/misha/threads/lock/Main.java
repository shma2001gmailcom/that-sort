package org.misha.threads.lock;

import java.util.Random;

/**
 * author: misha
 * date: 5/14/17
 * time: 11:14 AM
 */
class Main {

    public static void main(String... args) {
        final Counter shared = new Counter();
        for (short i = 0; i < 100; ++i) {
            final short finalI = i;
            new Thread(new Incrementer(shared) {

                @Override
                protected String prefix() {
                    return String.valueOf((char) finalI);
                }
            }).start();
        }
    }

    private static class Incrementer implements Runnable {
        private final Counter shared;

        Incrementer(final Counter counter) {
            this.shared = counter;
        }

        @Override
        public void run() {
            for (; ; ) {
                if (new Random().nextLong() % 1111395 == 1) {
                    int inc = shared.inc();
                    System.err.println(prefix() + " " + inc);
                }
            }
        }

        protected String prefix() {
            return " F ";
        }
    }
}

