package org.misha.interview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Thread.currentThread;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * given an array of integers and an integer s,
 * find representations of s as a sum of the elements of the array
 */
public class GivenSumAndTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("all")
    private static String resource(String name) {
        try {
            InputStream in = currentThread().getContextClassLoader().getResourceAsStream(name);
            return IOUtils.toString(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void check(String name, Set<SumSet> sumSets) throws JsonProcessingException {
        Set<SumSet> expected = mapper.readValue(resource(name), new TypeReference<Set<SumSet>>() {
        });
        assertTrue(expected.size() <= sumSets.size());
        expected.forEach(set -> assertTrue(": " + set, sumSets.contains(set)));
    }

    /**
     * given an array of integers and an integer s, find a pair of elements with the sum s if present
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
     * given an array ar of integers and an integer s,
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
                for (int c : complementIndexes) {
                    if (c != i) {
                        result.add(newHashSet(i, c));
                    }
                }
            }
        }
        return result;
    }

    /**
     * given an array of integers and an integer s,
     * find all the subsets of a cardinality 3 of the indices,
     * such that the sum of the array elements, having indices in such a subset, equals to s
     */
    Set<Set<Integer>> givenSumTriples(int s, int[] array) {
        Set<Set<Integer>> result = new HashSet<>();
        for (int i = 0; i < array.length; ++i) {
            int a = array[i];
            Set<Set<Integer>> temp = givenSumPairs(s - a, array);
            for (Set<Integer> set : temp) {
                if (!set.contains(i)) {
                    set.add(i);
                    result.add(set);
                }
            }
        }
        return result;
    }

    /**
     * given an array of integers and an integer s,
     * find all the subsets of the cardinality card of the indices,
     * such that the sum of the array elements, having indices in such a subset, equals to s
     */
    Set<Set<Integer>> givenSumTuples(int s, int[] array, int card) {
        Set<Set<Integer>> result = new HashSet<>();
        if (card < 1) return new HashSet<>();
        if (card == 1) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == s) result.add(newHashSet(i));
            }
            return result;
        }
        if (card == 2) return givenSumPairs(s, array);
        for (int i = 0; i < array.length; ++i) {
            int a = array[i];
            Set<Set<Integer>> temp = givenSumTuples(s - a, array, card - 1);
            if (temp.isEmpty()) continue;
            for (Set<Integer> set : temp) {
                if (!set.contains(i)) {
                    set.add(i);
                    result.add(set);
                }
            }
        }
        return result;
    }

    /**
     * given an array of integers and an integer s,
     * find all the subsets of the indices,
     * such that the sum of the array elements, having indices in such a subset, equals to s.
     * The sum of the set containing single element is this element
     */
    Set<Set<Integer>> givenSumTuplesTotal(int s, int[] array) {
        Set<Set<Integer>> result = newHashSet();
        for (int card = 1; card < array.length; ++card) {
            result.addAll(givenSumTuples(s, array, card));
        }
        return result;
    }

    @Test
    public void testPair() {
        int[] array = {1, 4, 1, 1, 4, 1};
        assertEquals(new ImmutablePair<>(0, 1), givenSumPair(5, array));
        array = new int[]{3, 1, 6, 9, 4, 11};
        assertEquals(new ImmutablePair<>(0, 5), givenSumPair(14, array));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testPairs() {
        int[] array = {1, 4, 1, 1, 4, 1};
        assertEquals(newHashSet(newHashSet(0, 1), newHashSet(1, 2), newHashSet(0, 4), newHashSet(1, 3), newHashSet(1, 5), newHashSet(4, 2), newHashSet(4, 3), newHashSet(4, 5)), givenSumPairs(5, array));
        array = new int[]{12, 3, 1, 6, 9, 4, 11};
        assertEquals(newHashSet(newHashSet(0, 1), newHashSet(4, 3), newHashSet(5, 6)), givenSumPairs(15, array));
    }

    @Test
    public void testTriples() throws JsonProcessingException {
        int[] array = {0, 0, 1, 0, 1, 2, 3, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5, 6};
        Set<Set<Integer>> sets = givenSumTriples(6, array);
        Set<SumSet> sumSets = asSumSets(sets, array);
        check("sum-sets.json", sumSets);
        int[] a = new int[]{1, 1, 1, 1};
        sets = givenSumTriples(3, a);
        assertEquals(4, sets.size());
        int[] b = new int[]{1, 1, 1, 0};
        sets = givenSumTriples(2, b);
        assertEquals(3, sets.size());
    }

    @Test
    public void testTuples() throws JsonProcessingException {
        int[] a = {2, 1, -2, 1, 3, 0, 1, 2, 1, 2};
        check("tuple-card-7.json", asSumSets(givenSumTuples(5, a, 7), a));
        int[] b = new int[]{2, 3, 6, 0, -1, 2, 3, 4, -1};
        check("tuple-card-5.json", asSumSets(givenSumTuples(7, b, 5), b));
    }

    @Test
    public void testTuplesTotal() throws JsonProcessingException {
        int[] a = {2, 1, -2, 1, 3, 0, 1, 2, 1, 2};
        System.out.println(writeResult(a));
        check("tuple-total-5.json", asSumSets(givenSumTuplesTotal(5, a), a));
        int[] b = new int[]{2, 3, 6, 0, -1, 2, 3, 4, -1};
        System.out.println(writeResult(b));
        check("tuple-total-7.json", asSumSets(givenSumTuplesTotal(7, b), b));
    }

    private String writeResult(int[] a) throws JsonProcessingException {
        return mapper.writeValueAsString(givenSumTuplesTotal(5, a).stream().map(set -> new SumSet(a, set)).collect(toSet()));
    }

    private Set<SumSet> asSumSets(Set<Set<Integer>> tuples, int[] array) {
        return tuples.stream().map(set -> new SumSet(array, set)).collect(toSet());
    }

    public static class SumSet {
        private Set<Integer> set;
        private int sum;

        @SuppressWarnings("unused")
        public SumSet() {
        }

        public SumSet(int[] array, Set<Integer> set) {
            this.set = set;
            int s = 0;
            for (int i : set) {
                s += array[i];
            }
            sum = s;
        }

        @Override
        public String toString() {
            try {
                return mapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        public Set<Integer> getSet() {
            return set;
        }

        public void setSet(Set<Integer> set) {
            this.set = set;
        }

        @SuppressWarnings("unused")
        public int getSum() {
            return sum;
        }

        @SuppressWarnings("unused")
        public void setSum(int sum) {
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SumSet sumSet = (SumSet) o;
            return sum == sumSet.sum && set.equals(sumSet.set);
        }

        @Override
        public int hashCode() {
            return Objects.hash(set, sum);
        }
    }
}
