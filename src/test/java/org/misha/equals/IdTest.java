package org.misha.equals;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class IdTest {
    private final Id c0p = new Id("c", null, "p");
    private final Id cc0 = new Id("c", "c", null);
    private final Id c0P = new Id("c", null, "P");
    private final Id ccp = new Id("c", "c", "p");
    private final Id c00 = new Id("c", null, null);
    private final Id cCp = new Id("c", "C", "p");

    @Test
    public void testEquals() {
        Set<List<Object>> pow2 = Sets.cartesianProduct(
                ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp),
                ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp));
        pow2.forEach(input -> {
            System.out.println(input);
            assertTrue(new Premise(input.get(0).equals(input.get(1))).implies(input.get(0).hashCode() == input.get(1)
                                                                                                              .hashCode() && input
                    .get(1)
                    .equals(input.get(0))));
        });
        Set<List<Object>> pow3 = Sets.cartesianProduct(
                ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp),
                ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp),
                ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp));
        pow3.forEach(input -> {
            System.out.println(input);
            assertTrue((new Premise((input.get(0).equals(input.get(1)) && input.get(1).equals(input.get(2)))).implies(
                    input.get(0).equals(input.get(2)))));
        });
    }

    private static class Premise {
        private final boolean premise;

        private Premise(final boolean premise) {
            this.premise = premise;
        }

        boolean implies(final boolean conclusion) {
            return !premise || conclusion;
        }
    }
}