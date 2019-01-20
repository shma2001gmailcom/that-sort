package org.misha.equals;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IdTest {
    private final ImmutableList<String> second = ImmutableList.of("1", "null", "2");
    private final ImmutableList<String> third = ImmutableList.of("P", "null", "p");
    private final List<List<String>> args = Lists.cartesianProduct(second, third);
    private final Id ccP = new Id("c", "1", "P");//1
    private final Id ccp = new Id("c", "1", "p");//1
    private final Id cc0 = new Id("c", "1", null);//1
    private final Id c0P = new Id("c", null, "P");//2
    private final Id c0p = new Id("c", null, "p");//3
    private final Id c00 = new Id("c", null, null);//4
    private final Id cCP = new Id("c", "2", "P");//2
    private final Id cCp = new Id("c", "2", "p");//3
    private final Id cC0 = new Id("c", "2", null);//5
    private final List<Id> toCheck = new ArrayList<>();

    @Before
    public void before() {
        args.forEach(item -> toCheck.add(new Id("c", value(item.get(0)), value(item.get(1)))));
        assertEquals(9, args.size());
    }

    private String value(final String s) {
        return "null".equals(s) ? null : s;
    }

    @Test
    public void testEquals() {
        assertEquals(cc0, ccP);
        assertEquals(ccP, cc0);
        assertEquals(ccP, ccp);
        assertEquals(cCp, ccp);
        assertEquals(cc0, ccp);
        assertEquals(ccP, ccp);
        assertEquals(cCp, ccp);
        assertEquals(cCp, ccp);
        assertEquals(c0p, ccp);
        assertEquals(cCp, c0p);
        ImmutableList<Id> all = ImmutableList.of(c0p, cc0, c0P, ccp, c00, cCp, cC0, cCP, ccP);
        check(all);
    }

    private void check(final ImmutableList<Id> all) {
        assertEquals(9, all.size());
        List<List<Object>> pow2 = Lists.cartesianProduct(all, all);
        System.err.println(pow2.size());
        pow2.forEach(input -> {
            System.out.println(input);
            System.out.println(new Premise(input.get(0).equals(input.get(1)))
                                       .implies(input.get(0).hashCode() == input.get(1).hashCode()
                                                        && input.get(1).equals(input.get(0))));
        });
        List<List<Object>> pow3 = Lists.cartesianProduct(all, all, all);
        pow3.forEach(input -> {
            System.out.println(input);
            System.out.println((new Premise((input.get(0).equals(input.get(1)) && input.get(1)
                                                                                       .equals(input.get(2)))).implies(
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