package org.misha.equals;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class IdTest {
    private final Id c0p = new Id("c", null, "p");
    private final Id cc0 = new Id("c", "c", "null");
    private final Id c0P = new Id("c", null, "P");
    private final Id ccp = new Id("c", "c", "p");

    @Test
    public void testEquals() {
        Set<List<Id>> pow2 = Sets.cartesianProduct(
                ImmutableSet.of(c0p, cc0, c0P, ccp),
                ImmutableSet.of(c0p, cc0, c0P, ccp));
        pow2.forEach(input -> {
            System.out.println(input);
            assertTrue((!input.get(0).equals(input.get(1))
                    || (input.get(0).hashCode() == input.get(1).hashCode() && input.get(1).equals(input.get(0)))));
        });
        Set<List<Id>> pow3 = Sets.cartesianProduct(
                ImmutableSet.of(c0p, cc0, c0P, ccp),
                ImmutableSet.of(c0p, cc0, c0P, ccp),
                ImmutableSet.of(c0p, cc0, c0P, ccp));
        pow3.forEach(input -> {
            System.out.println(input);
            assertTrue((!(input.get(0).equals(input.get(1)) && input.get(1).equals(input.get(2)))
                    || (input.get(0).hashCode() == input.get(2).hashCode())));
        });
    }
}