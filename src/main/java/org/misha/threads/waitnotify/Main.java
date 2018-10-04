package org.misha.threads.waitnotify;

import java.util.Random;

/**
 * author: misha
 * date: 5/14/17
 * time: 10:31 AM
 */
public class Main {
    public static void main(String... args) {
        new Thread(() -> new WaitBase() {

            void print() {
                for (; ; ) {
                    if (new Random().nextInt() % 2 != 0) {
                        doWait();
                    }
                    System.err.println("50");
                    doNotify();
                }
            }
        }.print()).start();
        new Thread(() -> new WaitBase() {

            void print() {
                for (; ; ) {
                    System.err.println("10");
                }
            }
        }.print());
    }
}