package org.misha.singletons.lazy.doublecheck.immutable;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.misha.singletons.FinalValue;
import org.misha.singletons.LoaderInterface;

import static java.lang.Thread.currentThread;

/**
 * author: misha
 * date: 1/21/17
 * time: 4:12 PM
 */
public class FinalValueSingleton {
    private static FinalValue<LoaderInterface> loaderValue;

    public LoaderInterface getLoader() {
        FinalValue<LoaderInterface> local = loaderValue;
        if (local == null) {
            synchronized (this) {
                if (loaderValue == null) {
                    loaderValue = new FinalValue<LoaderInterface>(
                            new Loader(StringUtils.EMPTY, currentThread().getContextClassLoader()));
                }
                local = loaderValue;
            }
        }
        return local.value();
    }

    private static class Loader implements LoaderInterface {
        private static final Logger log = Logger.getLogger(Loader.class);
        private static final long serialVersionUID = -7474171808685004618L;
        private final String name;
        private final transient ClassLoader classloader;

        private Loader(String name, ClassLoader classloader) {
            this.name = name;
            this.classloader = classloader;
        }

        public String getName() {
            return name;
        }

        public void printLoaders() {
            ClassLoader loader = this.classloader;
            while (loader != null) {
                log.info(loader.getClass().getSimpleName());
                loader = loader.getParent();
            }
        }
    }
}

