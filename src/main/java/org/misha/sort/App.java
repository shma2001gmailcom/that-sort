package org.misha.sort;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * author: misha
 * date: 9/15/14 10:45 PM.
 */
class UnitThread implements Runnable {
    private final int integer;
    private final CountDownLatch latch;
    private final List<Integer> list;

    public UnitThread(int i, CountDownLatch cdl, List<Integer> l) {
        integer = i;
        latch = cdl;
        list = l;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(8L * integer);
            synchronized (this) {
                list.add(integer);
            }
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Actor implements Runnable {
    private final CountDownLatch latch;

    private Actor(final CountDownLatch latch) {
        this.latch = latch;
    }

    public static Actor getInstance(final CountDownLatch latch) {
        return new Actor(latch);
    }

    @Override
    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    private final static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

    public static void main(String[] args) throws InterruptedException {
        int[] data = new int[170];
        int length = data.length;
        for (int i = 0; i < length; ++i) {
            data[i] = new Random().nextInt(100) + 1;
        }
        sort(data);
    }

    private static void sort(int[] data) {
        App app = new App();
        int length = data.length;
        int errors;
        int[] result = new int[data.length];
        System.arraycopy(data, 0, result, 0, data.length);
        errors = 0;
        result = app.waitForResult(Arrays.copyOf(data, data.length));
        int current = result[0];
        for (int i = 1; i < length; ++i) {
            if (current > result[i]) {
                errors++;
            }
            current = result[i];
        }
        System.err.println(Arrays.toString(result));
        System.err.println("error count: " + errors);
    }

    public synchronized int[] waitForResult(int[] data) {
        int dataLength = data.length;
        CountDownLatch latch = new CountDownLatch(data.length);
        new Thread(Actor.getInstance(latch)).start();
        for (int number : data) {
            Thread t = new Thread(new UnitThread(number, latch, list));
            t.start();
        }
        while (list.size() < dataLength) {
            try {
                wait(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int[] result = new int[dataLength];
        for (int i = 0; i < dataLength; ++i) {
            result[i] = list.get(i);
        }
        return result;
    }
}
