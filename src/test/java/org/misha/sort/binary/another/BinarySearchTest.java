package org.misha.sort.binary.another;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.misha.sort.binary.BinarySearch;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class BinarySearchTest {
    @Test
    public void testSearch() throws Exception {
        List<Integer> data = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertTrue(data.stream()
                .map(i -> new BinarySearch<>(data).search(i) == i)
                .reduce((x, y) -> x && y)
                .orElse(false));
    }

    @Test
    public void testSearchObjects() {
        List<O> list = Lists.newArrayList(null, new O(), new O(), new O(), null, null);
        List<O> cleared = list.stream().filter(Objects::nonNull).collect(toList());
        cleared.sort(Comparator.comparingInt(Object::hashCode));
        cleared.forEach(o -> {
            int expected = new BinarySearch<>(cleared).search(o);
            int actual = cleared.indexOf(o);
            if (expected != actual) {
                fail(expected + " != " + actual + " for o = " + o);
            }
        });
    }

    static class O implements Comparable<O> {
        @Override
        public int compareTo(@Nullable final O o) {
            return o == null ? 1 : hashCode() - o.hashCode();
        }
    }
}