package org.misha.fibo;

/**
 * author: misha
 * date: 10/27/16
 * time: 2:46 AM
 */
class Fibo {
    int calc(int n) {
        if (n < 3) return n < 1 ? 0 : 1;
        int y = 1;
        for (int i = 0, x = 1, t; i < n - 2; ++i, t = y, y += x, x = t);
        return y;
    }

    int fibo(int n, int a, int b) {
        return n < 1 ? 0 : n < 2 ? 1 : n == 2 ? b : fibo(n - 1, b, a + b);
    }
}
