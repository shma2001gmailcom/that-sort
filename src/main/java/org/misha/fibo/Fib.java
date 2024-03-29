package org.misha.fibo;

import java.math.BigInteger;

/**
 * author: misha
 * date: 10/27/16
 * time: 2:46 AM
 */
class Fib {
    static BigInteger bigInt(long val) {
        return BigInteger.valueOf(val);
    }

    int calc(int n) {
        if (n < 3) {
            return n < 1 ? 0 : 1;
        }
        int y = 1;
        for (int i = 0, x = 1, t; i < n - 2; ++i, t = y, y += x, x = t) ;
        return y;
    }

    long fib(long n, long a, long b) {
        return n < 1 ? 0 : n < 2 ? 1 : n == 2 ? b : fib(n - 1, b, a + b);
    }

    BigInteger fibonacci(int n, BigInteger a, BigInteger b) {
        return n < 1 ? bigInt(0) : n < 2 ? bigInt(1) : n == 2 ? b : fibonacci(n - 1, b, a.add(b));
    }
}
