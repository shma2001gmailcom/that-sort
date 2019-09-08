package org.misha.sort.that;

import java.util.Random;

import static java.lang.System.err;

/**
 * author: misha
 * date: 1/2/17
 * time: 2:48 PM
 */
public class Main {
    public static void main(String[] args) {
        int[] data = new int[3000];
        int length = data.length;
        for (int i = 0; i < length; ++i) {
            data[i] = 1 + new Random().nextInt(900);
        }
        final App app = new App(data);
        app.sort();
        err.println(app.getErrorCount());
    }
}
