package org.misha.merge;

import org.apache.log4j.Logger;

/**
 * Author: mshevelin
 * Date: 9/17/14
 * Time: 5:36 PM
 */
//    int a = 1234, b = 5678,  c = 15263748
//    int merge (int a, int b) {
//    }
public class Merge {
    private static final Logger log = Logger.getLogger(Merge.class);

    private static int[] code(int a) {
        final int[] digits = new int[digitCount(a)];
        int i = 0;
        while (a > 0) {
            digits[i++] = a % 10;
            a /= 10;
        }
        return digits;
    }

    private static int digitCount(int a) {
        int count = 0;
        while (a > 0) {
            a /= 10;
            ++count;
        }
        return count;
    }

    public static int merge(final int a, final int b) {
        int result = 0;
        int length = code(a).length;
        for (int i = 0; i < length; result = doMerge(a, b, result, i++));
        return result;
    }

    private static int doMerge(int a, int b, int result, int i) {
        result += code(a)[i] * (int) Math.pow(10, 2 * i + 1);
        result += code(b)[i] * (int) Math.pow(10, 2 * i);
        return result;
    }

    public static void main(final String[] args) {
        log.info(merge(1357, 2468));
    }
}
