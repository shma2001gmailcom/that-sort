package org.misha.sort;

import java.util.Random;

/**
 * author: misha
 * date: 1/2/17
 * time: 2:48 PM
 */
public class Main {
    public static void main(String[] args) {
        int[] data = new int[5000];
        int length = data.length;
        for (int i = 0; i < length; ++i) {
            data[i] = 1 + new Random().nextInt(100);
        }
        new App(data).sort();
    }
}
