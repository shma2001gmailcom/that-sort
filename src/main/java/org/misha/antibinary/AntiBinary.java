package org.misha.antibinary;

import java.security.SecureRandom;
import java.util.Arrays;

import static java.lang.Math.log;

public class AntiBinary {
    private final int[] a;
    private final int n;
    private long count;

    private AntiBinary(final int[] a) {
        this.a = a;
        n = a.length;
    }

    private boolean swap(int i, int j) {
        if (a[i] > a[j] && i < j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            return true;
        }
        return false;
    }

    private boolean doOnce() {
        boolean result = false;
        for (int i = 0; i + 1 < n; i += 2) {
            result |= swap(i, i + 1);
            ++count;
        }
        for (int i = 1; i + 1 < n; i += 2) {
            result |= swap(i, i + 1);
            ++count;
        }
        return result;
    }

    public static void main(String... args) {
        int size = 2 << 17;
        int[] a = new int[size];
        for (int i = 0; i < size; ++i) {
            a[i] = new SecureRandom().nextInt(1100200);
        }
        AntiBinary ab = new AntiBinary(a);
        while (ab.doOnce()) ;
        System.out.println(ab.count);
        System.out.println((ab.count / (size * log(size))));
    }
}
