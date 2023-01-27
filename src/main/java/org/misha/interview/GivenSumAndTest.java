package org.misha.interview;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

public class GivenSumAndTest {
    /**
     * given an array of integers and integer s, find a pair of elements with the sum s if present
     */
    ImmutablePair<Integer, Integer> givenSumPair(int sum, int[] array) {
        Map<Integer, Integer> complements = new HashMap<>();
        for (int i = 0; i < array.length; ++i) {
            complements.putIfAbsent(sum - array[i], i);
        }
        for (int i = 0; i < array.length; ++i) {
            Integer complementIndex = complements.get(array[i]);
            if (complementIndex != null && complementIndex != i) {
                return new ImmutablePair<>(i, complementIndex);
            }
        }
        return null;
    }

    /**
     * given an array ar of integers and integer s,
     * find all such unordered pairs of indices i, j that ar[i] + ar[j] = s
     */
    Set<Set<Integer>> givenSumPairs(int sum, int[] array) {
        Map<Integer, List<Integer>> complements = new HashMap<>();
        for (int i = 0; i < array.length; ++i) {
            int key = sum - array[i];
            if (complements.containsKey(key)) {
                complements.get(key).add(i);
            } else {
                complements.put(key, newArrayList(i));
            }
        }
        Set<Set<Integer>> result = new HashSet<>();
        for (int i = 0; i < array.length; ++i) {
            List<Integer> complementIndexes = complements.get(array[i]);
            if (complementIndexes != null) {
                for (Integer c : complementIndexes) {
                    if (c != i) {
                        result.add(newHashSet(i, c));
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] array = {1, 4, 1, 1, 4, 1};
        assertEquals(new ImmutablePair<>(0, 1), givenSumPair(5, array));
        array = new int[]{3, 1, 6, 9, 4, 11};
        assertEquals(new ImmutablePair<>(0, 5), givenSumPair(14, array));
    }
    @SuppressWarnings("unchecked")
    @Test
    public void testAll() {
        int[] array = {1, 4, 1, 1, 4, 1};
        assertEquals( newHashSet(newHashSet(0, 1), newHashSet(1, 2),
                newHashSet(0, 4), newHashSet(1, 3), newHashSet(1, 5),
                newHashSet(4, 2), newHashSet(4, 3), newHashSet(4, 5)),
                givenSumPairs(5, array));
        array = new int[]{12, 3, 1, 6, 9, 4, 11};
        assertEquals(newHashSet(newHashSet(0, 1), newHashSet(4, 3), newHashSet(5, 6)),
                givenSumPairs(15, array));
    }
}
