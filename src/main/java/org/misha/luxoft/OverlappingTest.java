package org.misha.luxoft;

import org.junit.Test;

import static org.misha.luxoft.Overlapping.range;

public class OverlappingTest {
    @Test
    public void test() {
        Overlapping o = new Overlapping(range(0, 6),
                                        range(3, 8),
                                        range(7, 9),
                                        range(10, 13),
                                        range(15, 18),
                                        range(15, 19),
                                        range(20, 55));
        System.out.println(o);
        System.out.println(o.merge());
    }
}
