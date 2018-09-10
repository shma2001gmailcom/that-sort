package org.misha.threads.singletons.enums;

import org.junit.Test;
import org.misha.threads.singletons.LoaderInterface;

import static org.junit.Assert.assertSame;

/**
 * author: misha
 * date: 1/21/17
 * time: 6:01 PM
 */
public class LoaderEnumTest {

    @Test
    public void check() {
        final LoaderInterface loader = LoaderEnum.LOADER.getLoader();
        for (int i = 0; i < 1000; ++i) {
            new Thread(() -> assertSame(loader, LoaderEnum.LOADER.getLoader())).start();
        }
    }
}