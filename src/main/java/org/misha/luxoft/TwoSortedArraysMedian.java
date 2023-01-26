package org.misha.luxoft;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoSortedArraysMedian {
    private static double median(int[] a) {
        int length = a.length;
        return length % 2 == 1 ? a[length / 2] : (a[length / 2] + a[(length - 1) / 2]) / 2.0;
    }

    private static double median(int[] a, int[] b) {
        return median(merge(a, b));
    }

    private static int[] merge(int[] a, int[] b) {//O(a.length + b.length)
        final int[] left;
        final int[] right;
        if (b.length >= a.length) {//shortest is left
            left = a;
            right = b;
        } else {
            left = b;
            right = a;
        }
        final int[] result = new int[left.length + right.length];
        final Irl irl = new Irl(0, 0, 0);
        int[] minMax = minMax(left[irl.l], right[irl.r]);
        while (irl.l < left.length && irl.r < right.length && irl.i < result.length) {
            if (minMax[0] == left[irl.l]) {
                minMax = leftLess(left, right, result, irl, minMax);
                minMax = rightMore(left, right, result, irl, minMax);
            } else {
                minMax = rightLess(left, right, result, irl, minMax);
                minMax = leftMore(left, right, result, irl, minMax);
            }
        }
        for (int k = irl.r, j = irl.i; k < right.length; ++k, ++j) {
            result[j] = right[k];
        }
        return result;
    }

    private static int[] leftMore(final int[] left,
                                  final int[] right,
                                  final int[] result,
                                  final Irl irl,
                                  int[] minMax
    ) {
        while (irl.l < left.length && minMax[1] == left[irl.l]) {
            result[irl.i] = minMax[1];
            irl.incI().incL();
            if (irl.l < left.length && irl.r < right.length) {
                minMax = minMax(left[irl.l], right[irl.r]);
            }
        }
        return minMax;
    }

    private static int[] rightLess(final int[] left,
                                   final int[] right,
                                   final int[] result,
                                   final Irl irl,
                                   int[] minMax
    ) {
        while (irl.r < right.length && minMax[0] == right[irl.r]) {
            result[irl.i] = minMax[0];
            irl.incI().incR();
            if (irl.l < left.length && irl.r < right.length) {
                minMax = minMax(right[irl.r], left[irl.l]);
            }
        }
        return minMax;
    }

    private static int[] rightMore(final int[] left,
                                   final int[] right,
                                   final int[] result,
                                   final Irl irl,
                                   int[] minMax
    ) {
        while (irl.r < right.length && minMax[1] == right[irl.r]) {
            result[irl.i] = minMax[1];
            irl.incI().incR();
            if (irl.l < left.length && irl.r < right.length) {
                minMax = minMax(left[irl.l], right[irl.r]);
            }
        }
        return minMax;
    }

    private static int[] leftLess(final int[] left,
                                  final int[] right,
                                  final int[] result,
                                  final Irl irl,
                                  int[] minMax
    ) {
        while (irl.l < left.length && minMax[0] == left[irl.l]) {
            result[irl.i] = minMax[0];
            irl.incI().incL();
            if (irl.l < left.length && irl.r < right.length) {
                minMax = minMax(left[irl.l], right[irl.r]);
            }
        }
        return minMax;
    }

    static final class Irl {
        private int i;
        private int r;
        private int l;

        private Irl(int i, int r, int l) {
            this.i = i;
            this.r = r;
            this.l = l;
        }

        private Irl incI() {
            ++i;
            return this;
        }

        private void incR() {
            ++r;
        }

        private void incL() {
            ++l;
        }
    }

    private static int[] minMax(int a, int b) {
        return new int[]{min(a, b), -min(-a, -b)};
    }

    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    private static boolean areEqual(int[] a, int[] b) {
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        assertEquals(3.5, median(new int[]{1, 2, 3, 4, 5, 6}), .0);
        int[] a = {1, 1, 4, 4, 5, 6, 8};
        int[] b = {2, 2, 3, 4, 5, 5, 6, 7, 9};
        assertTrue(areEqual(new int[]{1, 1, 2, 2, 3, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9},
                merge(a, b)));
        assertEquals(4.5, median(a, b), .0);
    }
}
