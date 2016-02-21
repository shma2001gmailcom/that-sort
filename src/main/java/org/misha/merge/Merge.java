package org.misha.merge;

/**
 * Author: mshevelin
 * Date: 9/17/14
 * Time: 5:36 PM
 */
//    int a = 1234, b = 5678,  c = 15263748
//    int merge (int a, int b) {
//    }
class Merge {

    private static int[] code(int a) {
        final int[] digits = new int[digitCount(a)];
        int i = 0;
        while (a > 0) {
            digits[i] = a % 10;
            a /= 10;
            ++i;
        }
        return digits;
    }

    private static int digitCount(final int a) {
        int b = a;
        int count = 0;
        while (b > 0) {
            b /= 10;
            ++count;
        }
        return count;
    }

    public static int merge(final int a, final int b) {
        int result = 0;
        for (int i = 0; i < code(a).length; ++i) {
            result += code(a)[i] * (int) Math.pow(10, 2 * i + 1);
            result += code(b)[i] * (int) Math.pow(10, 2 * i);
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(merge(103, 987450));
    }
}
