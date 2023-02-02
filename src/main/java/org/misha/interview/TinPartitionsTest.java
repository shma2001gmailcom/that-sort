package org.misha.interview;

import org.junit.Test;

import java.util.stream.IntStream;

public class TinPartitionsTest {
    /**
     * Given an integer n, how many sequences containing 1 and 2 whose sums are equal to n there are?
     */
    static int part(int n) {
        if (n <= 1) return 1;
        int c = 1;
        for (int i = 1, p = 1, m; i < n; m = c, c = c + p, p = m, ++i) ;
        return c;
    }

    @Test
    public void testPart() {
        IntStream.range(0, 21).forEach(i -> System.err.println(i + "->" + part(i)));
    }

    /**
     * Abraham should save money to buy a new hard drive by applying the following policy:
     * Every Monday he decided to save one pound more than on the previous Monday.
     * On all other days of the week, he decided to save one pound more than on the previous day.
     * How much money will Abraham receive after n days of applying this policy?
     */
    int f(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n % 7 == 1) return f(n - 7) + 1;
        return f(n - 1) + 1;
    }

    int sum(int n) {
        int result = 0;
        for (int i = 1; i <= n; ++i) {
            result += f(i);
        }
        return result;
    }

    @Test
    public void test2() {
        IntStream.range(0, 8).forEach(i -> System.err.println(i + "->" + sum(i)));
    }
}
