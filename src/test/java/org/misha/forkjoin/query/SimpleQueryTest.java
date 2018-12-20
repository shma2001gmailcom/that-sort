package org.misha.forkjoin.query;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.misha.forkjoin.query.Operation.AND;
import static org.misha.forkjoin.query.Operation.OR;
import static org.misha.forkjoin.query.QueryPredicate.EQUALS;
import static org.misha.forkjoin.query.QueryPredicate.LIKE;

public class SimpleQueryTest {
    @Test
    public void evaluate() throws Exception {
        Query query = new SimpleQuery("prop1", EQUALS, "prop1");
        final TestObject t = new TestObject("prop1", 0);
        final TestObject s = new TestObject("A", 1);
        assertTrue(query.evaluate().test(t));
        Query query1 = new SimpleQuery("prop2", LIKE, 0);
        assertTrue(query1.evaluate().test(t));
        ComplexQuery complexQuery = new ComplexQuery(AND, query, query1);
        assertTrue(complexQuery.evaluate().test(t));
        ComplexQuery extraComplex = new ComplexQuery(OR, complexQuery, query1);
        assertFalse(extraComplex.evaluate().test(s));
        assertTrue(extraComplex.evaluate().test(t));

    }

    private static class TestObject {
        String prop1;
        int prop2;

        private TestObject(final String prop1, final int prop2) {
            this.prop1 = prop1;
            this.prop2 = prop2;
        }

        public String getProp1() {
            return prop1;
        }

        public int getProp2() {
            return prop2;
        }
    }
}