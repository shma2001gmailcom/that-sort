package org.misha.threads.singletons.lazy.holder;

import org.junit.Test;
import org.misha.threads.singletons.LoaderInterface;

import static org.junit.Assert.assertSame;

/**
 * author: misha
 * date: 1/21/17
 * time: 5:46 PM
 */
public class LoaderHolderTest {

    @Test
    public void check() {
        final LoaderInterface loader = LoaderHolder.getLoader();
        for (int i = 0; i < 1000; ++i) {
            new Thread(() -> assertSame(loader, LoaderHolder.getLoader())).start();
        }
    }
}