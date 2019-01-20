package org.misha.merge;

import org.apache.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * Author: mshevelin
 * Date: 9/17/14
 * Time: 5:36 PM
 * long a = 1234, b = 5678,  c = 15263748
 * long merge (long a, long b) {
 * }
 */
public class Merge {
    private static final Logger log = Logger.getLogger(Merge.class);

    private static long[] code(long a) {
        final long[] digits = new long[digitCount(a)];
        int i = 0;
        while (a > 0) {
            digits[i++] = a % 10;
            a /= 10;
        }
        return digits;
    }

    private static int digitCount(long a) {
        int count = 0;
        while (a > 0) {
            a /= 10;
            ++count;
        }
        return count;
    }

    private static long merge(final long a, final long b) {
        long result = 0;
        final long length = code(a).length;
        int i = 0;
        while (i < length) {
            result = doMerge(a, b, result, i++);
        }
        return result;
    }

    private static long doMerge(final long a, final long b, long result, final int i) {
        result += code(a)[i] * pow(1_0, 2 * (long) i + 1);
        result += code(b)[i] * pow(10, (long) 2 * i);
        return result;
    }

    public static void main(final String[] args) {
        log.info(merge(12345678L, 98765432L));
        log.info(merge("msaseei", "ih hvln, an author"));
    }

    private static long pow(long base, long exp) {
        long result = 1;
        long i = 0;
        while (i < exp) {
            result *= base;
            ++i;
        }
        return result;
    }

    private static String merge(@Nonnull String left, @Nonnull String right) {
        int leftLength = left.length();
        int rightLength = right.length();
        int min = Math.min(leftLength, rightLength);
        char[] leftCh = left.substring(0, min).toCharArray();
        char[] rightCh = right.substring(0, min).toCharArray();
        return mergeCommon(leftCh, rightCh) + (min == leftLength ? right.substring(min) : left.substring(min));
    }

    private static String mergeCommon(char[] leftCh, char[] rightCh) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < leftCh.length + rightCh.length; ++i) {
            int m = i % 2;
            int half = i / 2;
            if (m == 0 && half < leftCh.length) {
                sb.append(leftCh[half]);
            }
            if (m == 1 && half < rightCh.length) {
                sb.append(rightCh[half]);
            }
        }
        return sb.toString();
    }
}
