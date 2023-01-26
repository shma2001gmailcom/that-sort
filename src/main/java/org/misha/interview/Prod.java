package org.misha.interview;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.stream.Collectors.toMap;

/**
 * /*
 * Write a function that takes in a non-empty list of integers and returns
 * a list of the same length, where each k-th element is equal to the product
 * of elements with indices i != k in the original list.
 * The implementation should not use division.
 * <p>
 * Examples:
 * [1, 2, 3, 4, 5] -> [120, 60, 40, 30, 24]
 * [0, 2, 3, 4, 5] -> [120,  0,  0,  0,  0]
 * [1, 2, 3, 4, 0] -> [  0,  0,  0,  0, 24]
 */
public class Prod {//time: O(n * n), space: O(3 * n + (2 * n) * (2 * n)) = O(n * n)

    public static void main(String[] args) {
        List<Integer> a = newArrayList(1, 2, 3, 4, 5);
        List<Integer> b = f(a);
        System.out.println(b);
    }

    private static List<Integer> f(List<Integer> a) {
        AtomicInteger index = new AtomicInteger();
        Map<Integer, Integer> numbers = a.stream().
                collect(toMap(val -> index.getAndIncrement(), val -> val));//O(n)
        Map<Integer, Collection<Integer>> map = new HashMap<>();
        IntStream.range(0, a.size()).forEach(i -> {//O(n)
            HashMap<Integer, Integer> exceptCurrent = newHashMap(numbers);
            exceptCurrent.remove(i);//O(1)
            map.put(i, exceptCurrent.values());
        });
        return map.values().stream().map(Prod::prod).collect(Collectors.toList());//O(n * n)
    }

    private static int prod(Collection<Integer> list) {
        return list.stream().reduce(1, (x, y) -> x * y);//O(n)
    }
}
