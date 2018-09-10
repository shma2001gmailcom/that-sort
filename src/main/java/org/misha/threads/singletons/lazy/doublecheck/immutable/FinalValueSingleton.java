package org.misha.threads.singletons.lazy.doublecheck.immutable;

import org.apache.log4j.Logger;
import org.misha.threads.singletons.FinalValue;
import org.misha.threads.singletons.LoaderInterface;

import static java.lang.Thread.currentThread;
import static org.apache.commons.lang3.StringUtils.EMPTY;

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
                    loaderValue = new FinalValue<>(
                            new Loader(EMPTY, currentThread().getContextClassLoader()));
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

