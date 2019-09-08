package org.misha.sort.binary;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTest {
    @Test
    public void testSearch() throws Exception {
        List<Integer> data = Lists.newArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertTrue(data.stream()
                      .map(i -> new BinarySearch<>(data).search(i) == i)
                      .reduce((x, y) -> x && y)
                      .orElse(false));
    }
}