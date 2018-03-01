package org.misha.threads.singletons;

import org.misha.threads.singletons.enums.LoaderEnum;
import org.misha.threads.singletons.lazy.doublecheck.immutable.FinalValueSingleton;
import org.misha.threads.singletons.lazy.doublecheck.volatyle.DoubleCheck;
import org.misha.threads.singletons.lazy.holder.LoaderHolder;

/**
 * author: misha
 * date: 1/21/17
 * time: 4:27 PM
 */
public class Launcher {

    public static void main(String[] args) {
        LoaderInterface[] loaders =
                {new FinalValueSingleton().getLoader(), new DoubleCheck().getLoader(), LoaderHolder.getLoader(),
                 LoaderEnum.LOADER.getLoader()
                };
        for (final LoaderInterface loader : loaders) {
            loader.printLoaders();
        }
    }
}

