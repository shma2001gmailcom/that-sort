package org.misha.luxoft;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.misha.luxoft.MultiList.withChildren;
import static org.misha.luxoft.MultiList.withData;

public class MultiListTest {

    @Test
    public void test() {
        testCommon();
        testSingleton();
        testEmpty();
    }

    private void testEmpty() {
        assertEquals(new ArrayList<>(), withChildren().asList());
    }

    private void testSingleton() {
        assertEquals(new ArrayList<Integer>() {{add(1);}}, withData(1).asList());
    }

    private void testCommon() {
        MultiList x =
                withChildren(
                        withChildren(
                                withData(1),
                                withData(6),
                                withChildren(
                                        withData(3),
                                        withData(4),
                                        withData(5)
                                )
                        ),
                        withData(2)
                );
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(6);
            add(3);
            add(4);
            add(5);
            add(2);
        }};
        assertEquals(expected, x.asList());
    }
}
