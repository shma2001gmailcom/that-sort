package org.misha.forkjoin;

import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;

public class ForkJoinSearchTest {
    @Test
    public void testSearch() {
        assertEquals(3, new ForkJoinSearch<>().search(
                newArrayList("c", "a", "c", "b", "c", "d"),
                "c"::equals
        ).size());
    }

    @Test
    public void testSearch0() {
        assertEquals(0, new ForkJoinSearch<>().search(
                newArrayList(),
                "c"::equals
        ).size());
    }
}