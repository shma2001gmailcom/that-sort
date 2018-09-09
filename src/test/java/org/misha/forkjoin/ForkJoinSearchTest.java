package org.misha.forkjoin;

import org.junit.Test;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;

public class ForkJoinSearchTest {

    @Test
    public void testSearch() throws Exception {
        assertEquals(3, new ForkJoinSearch<>().search(
                newArrayList("c", "a", "c", "b", "c", "d"),
                "c"::equals
        ).size());
    }
}