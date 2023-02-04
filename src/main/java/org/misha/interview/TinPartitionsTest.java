package org.misha.interview;

import org.junit.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;
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
    static int policy(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n % 7 == 1) return policy(n - 7) + 1;
        return policy(n - 1) + 1;
    }

    static int sum(int n) {
        int result = 0;
        for (int i = 1; i <= n; ++i) {
            result += policy(i);
        }
        return result;
    }

    /**
     * Даны три неубывающих массива чисел. Найти число, которое присутствует во всех трех массивах.
     */
    static int common(int[] a, int[] b, int[] c) {
        int i = 0, j = 0, k = 0;
        int x = a[0];
        int y = b[0];
        int z = c[0];
        int min;
        while (x != y || x != z) {
            min = min(x, y, z);
            if (min == x) {
                ++i;
                if (i < a.length) {
                    x = a[i];
                } else {
                    throw new NoSuchElementException();
                }
            }
            if (min == y) {
                ++j;
                if (j < b.length) {
                    y = b[j];
                } else {
                    throw new NoSuchElementException();
                }
            }
            if (min == z) {
                ++k;
                if (k < c.length) {
                    z = c[k];
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        return x;
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static int min(int a, int b, int c) {
        return min(a, min(b, c));
    }

    @Test
    public void testPolicy() {
        IntStream.range(0, 8).forEach(i -> System.err.println(i + "->" + sum(i)));
    }

    @Test
    public void testFind() {
        int[] a = {1, 2, 7, 9};
        int[] b = {3, 9, 11};
        int[] c = {2, 3, 4, 9, 10};
        System.err.println(common(a, b, c));
    }

    /**
     * Даны 2 отсортированных массива с уникальными элементами,
     * найти и вывести их упорядоченное объединение без дубликатов,
     * используя константу доп. памяти.
     * Input : arr1[] = {1, 3, 4, 5, 7}
     * arr2[] = {2, 3, 5, 6}
     * Output : Union = {1, 2, 3, 4, 5, 6, 7}
     */
    void union(int[] a, int[] b) {
        int i = 0, j = 0;
        int c = 0;
        while (i < a.length || j < b.length) {
            if (i < a.length) {
                if (a[i] > c) {
                    System.out.println(c + "  a->" + a[i]);
                    c = a[i];
                }
                ++i;
            }
            if (j < b.length) {
                if (b[j] > c) {
                    System.out.println(c + "  b->" + b[j]);
                    c = b[j];
                }
                ++j;
            }
        }
    }

    @Test
    public void testUnion() {
        int[] a = {1, 3, 4, 5, 7};
        int[] b = {2, 3, 5, 6, 8};
        union(a, b);
    }

    /**
     * Необходимо найти ОДНУ пару непересекающихся отрезков.
     * Пример: [[20, 30], [19, 21], [20, 26], [29, 35]] -> [[19, 21], [29, 35]] || [[20, 26], [29, 35]]
     */
    void disjoint(int[][] a) {
        for (int i = 0; i < a.length; ++i) {
            for (int j = i + 1; j < a.length; ++j) {
                if (a[i][1] < a[j][0]) {
                    System.err.println(Arrays.toString(a[i]) + " " + Arrays.toString(a[j]));
                    //return;
                }
            }
        }
    }

    @Test
    public void testDisjoint() {
        disjoint(new int[][]{new int[]{20, 30}, new int[]{19, 21}, new int[]{20, 26}, new int[]{29, 35}});
    }
}
