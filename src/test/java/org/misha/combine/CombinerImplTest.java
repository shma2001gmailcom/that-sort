package org.misha.combine;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author: misha
 * date: 2/11/18
 * time: 12:15 PM
 */
public class CombinerImplTest {
    private static final Logger log = Logger.getLogger(CombinerImplTest.class);

    @Test
    public void testCombiner() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 20; ++i) {
            list.add(new Random().nextInt(20));
        }
        Combiner<String, Integer> c = new CombinerImpl(list);
        c.combine();
        for (Integer i : c) {
            log.debug(i);
        }
    }
}