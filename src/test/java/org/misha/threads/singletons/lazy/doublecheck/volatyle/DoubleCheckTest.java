package org.misha.threads.singletons.lazy.doublecheck.volatyle;

import org.junit.Test;
import org.misha.threads.singletons.LoaderInterface;

import static org.junit.Assert.assertSame;

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
            new Thread(() -> assertSame(loader, new DoubleCheck().getLoader())).start();
        }
    }
}