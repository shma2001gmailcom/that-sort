package org.misha.fibo;

/**
 * author: misha
 * date: 10/27/16
 * time: 2:46 AM
 */
public class Fibo {
   private final int bound;

    public Fibo(final int n) {
        bound = n;
    }

    public int calc() {
        if(bound < 1) return 0;
        if(bound < 3) return 1;
        int y = 1;
        for(int i = 0, x = 1, t; i < bound - 2; ++i, t = y, y += x, x = t);
        return  y;
    }
}
