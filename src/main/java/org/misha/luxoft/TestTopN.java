package org.misha.luxoft;

import org.junit.Test;
import org.misha.interview.TopN;

public class TestTopN {
    @Test
    public void test() {
        TopN in = new TopN(2);
        int[] arr = new int[]{1, 1, 2};
        for (int num : arr) {
            in.push(num);
        }
        System.err.println(in.top());
    }

    @Test
    public void test1() {
        TopN in = new TopN(2);
        int[] arr = new int[]{7, 6, 5, 3, 1};
        for (int num : arr) {
            in.push(num);
        }
        System.err.println(in.top());
    }

    @Test
    public void test2() {
        TopN in = new TopN(2);
        int[] arr = new int[]{1, 3, 3};
        for (int num : arr) {
            in.push(num);
        }
        System.err.println(in.top());
    }

    @Test
    public void test3() {
        TopN in = new TopN(5);
        int[] arr = new int[]{1, 3, 3, 7, 7, 8, 9, 9, 10, 11, 11};
        for (int num : arr) {
            in.push(num);
        }
        System.err.println(in.top());
    }
}
