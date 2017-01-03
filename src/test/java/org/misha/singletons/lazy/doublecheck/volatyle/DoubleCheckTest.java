package org.misha.singletons.lazy.doublecheck.volatyle;

import org.junit.Test;
import org.misha.singletons.LoaderInterface;

import static org.junit.Assert.assertTrue;

/**
 * author: misha
 * date: 1/21/17
 * time: 5:21 PM
 */
public class DoubleCheckTest {
    @Test
    public void check() {
        final LoaderInterface loader = new DoubleCheck().getLoader();
        for (int i = 0; i < 1000; ++i) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    assertTrue(loader == new DoubleCheck().getLoader());
                }
            }).start();
        }
    }
}