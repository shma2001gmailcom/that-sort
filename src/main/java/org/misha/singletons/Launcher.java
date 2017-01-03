package org.misha.singletons;

import org.misha.singletons.enums.LoaderEnum;
import org.misha.singletons.lazy.doublecheck.immutable.FinalValueSingleton;
import org.misha.singletons.lazy.doublecheck.volatyle.DoubleCheck;
import org.misha.singletons.lazy.holder.LoaderHolder;

/**
 * author: misha
 * date: 1/21/17
 * time: 4:27 PM
 */
public class Launcher {

    public static void main(String[] args) {
        for (final LoaderInterface loader : new LoaderInterface[]{new FinalValueSingleton().getLoader(),
                                                                  new DoubleCheck().getLoader(),
                                                                  LoaderHolder.getLoader(),
                                                                  LoaderEnum.LOADER.getLoader()
        }) {
            loader.printLoaders();
        }
    }
}

