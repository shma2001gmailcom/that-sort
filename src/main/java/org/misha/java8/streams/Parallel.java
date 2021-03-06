package org.misha.java8.streams;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class Parallel {
    public static void main(String... args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        IntStream.range(0, 10000000).parallel().forEach(i -> map.put(String.valueOf(i), i));
        map.keySet().parallelStream().forEach(k -> assertEquals(map.get(k), Integer.valueOf(k)));
    }
}
