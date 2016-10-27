package org.misha.fibo;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * author: misha
 * date: 10/27/16
 * time: 9:05 PM
 */
public class FiboTest {
    private final int[] fibos = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144};

    @Test
    public void calc() throws Exception {
        for (int i = 1; i < 13; ++i) {
            assertTrue(new Fibo().calc(i) == fibos[i - 1]);
        }
    }

    @Test
    public void fibo() throws Exception {
        for (int i = 1; i < 13; ++i) {
            assertTrue(new Fibo().fibo(i, 1, 1) == fibos[i - 1]);
        }
    }
}