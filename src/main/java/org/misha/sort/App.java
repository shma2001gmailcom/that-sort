package org.misha.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.arraycopy;
import static java.lang.System.err;
import static java.util.Arrays.copyOf;

class App {
    private final List<Integer> list;
    private final int[] data;
    private volatile AtomicInteger errorCount;

    App(int[] data) {
        this.data = data;
        list = new ArrayList<>();
        errorCount = new AtomicInteger(0);
    }

    void sort() {
        int length = data.length;
        int[] result = new int[length];
        arraycopy(data, 0, result, 0, length);
        result = waitForResult(copyOf(data, length));
        err.println(Arrays.toString(result));
        final int errors = getErrors(length, result);
        err.println("error count: " + errors);
        errorCount.set(errors);
    }

    private int getErrors(int length, int[] result) {
        int errors = 0;
        int current = result[0];
        for (int i = 1; i < length; ++i) {
            if (current > result[i]) {
                errors++;
            }
            current = result[i];
        }
        return errors;
    }

    private int[] waitForResult(int[] data) {
        int dataLength = data.length;
        CountDownLatch latch = new CountDownLatch(dataLength);
        new Thread(Actor.getInstance(latch)).start();
        for (int number : data) {
            Thread t = new Thread(new UnitThread(number, latch, list));
            t.start();
        }
        synchronized (this) {
            while (list.size() < dataLength) {
                try {
                    wait(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        int[] result = new int[dataLength];
        for (int i = 0; i < dataLength; ++i) {
            result[i] = list.get(i);
        }
        return result;
    }
    
    int getErrorCount() {
        return errorCount.get();
    }
}
