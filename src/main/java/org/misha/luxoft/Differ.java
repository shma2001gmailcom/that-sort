package org.misha.luxoft;

import org.junit.Test;

//print all numbers from the first sorted array not containing in the second sorted array

public class Differ {

    private void differ(int[] first, int[] second) {
        int f = 0;
        int s = 0;
        while(f < first.length && s < second.length) {
            int a = first[f];
            int b = second[s];
            if (a > b) {
                ++s;
            } else {
                if (a < b) {
                    System.out.println(a);
                    ++f;
                } else {
                    ++f;
                    ++s;
                }
            }
        }
        for (; f < first.length; ++f) {
            System.out.println(first[f]);
        }
    }

    @Test
    public void test() {
        int[] a = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int[] b = {-2, 2, 2, 3, 3, 4, 4, 7, 7};
        differ(a, b);
    }
}
