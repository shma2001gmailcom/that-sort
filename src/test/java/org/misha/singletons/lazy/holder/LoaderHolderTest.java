package org.misha.singletons.lazy.holder;

import org.junit.Test;
import org.misha.singletons.LoaderInterface;

import static org.junit.Assert.assertTrue;

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
            new Thread(new Runnable() {

                @Override
                public void run() {
                    assertTrue(loader == LoaderHolder.getLoader());
                }
            }).start();
        }
    }
}