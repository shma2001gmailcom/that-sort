package org.misha.equals;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IdTest {
    private final Id ccP = new Id("c", "1", "P");
    private final Id ccp = new Id("c", "1", "p");
    private final Id cc0 = new Id("c", "1", null);
    private final Id c0P = new Id("c", null, "P");
    private final Id c0p = new Id("c", null, "p");
    private final Id c00 = new Id("c", null, null);
    private final Id cCP = new Id("c", "2", "P");
    private final Id cCp = new Id("c", "2", "p");
    private final Id cC0 = new Id("c", "2", null);


    @Test
    public void testEquals() {
        assertEquals(ccP, ccp);
        assertEquals(cCp, ccp);
        assertEquals(cc0, ccp);
        assertEquals(ccP, ccp);
        assertEquals(cCp, ccp);
        assertEquals(cCp, ccp);
        assertEquals(c0p, ccp);
        assertEquals(cCp, c0p);
        final ImmutableSet<Id> all = ImmutableSet.of(c0p, cc0, c0P, ccp, c00, cCp, cC0, cCP, ccP);
        Set<List<Object>> pow2 = Sets.cartesianProduct(all, all);
        System.err.println(pow2.size());
        int[] i = new int[1];
        pow2.forEach(input -> {
            System.out.println(i[0]++ + " " + input);
            assertTrue(new Premise(input.get(0).equals(input.get(1)))
                               .implies(input.get(0).hashCode() == input.get(1).hashCode()
                                                && input.get(1).equals(input.get(0))));
        });
        Set<List<Object>> pow3 = Sets.cartesianProduct(all, all, all);
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
            System.out.println(premise + " ==> " + conclusion);
            return !premise || conclusion;
        }
    }
}