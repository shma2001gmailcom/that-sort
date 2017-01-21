package org.misha.singletons.lazy.doublecheck.volatyle;

import org.apache.log4j.Logger;
import org.misha.singletons.LoaderInterface;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * author: misha
 * date: 1/21/17
 * time: 5:12 PM
 */
public class DoubleCheck {
    private static volatile LoaderInterface loader;

    public LoaderInterface getLoader() {
        LoaderInterface result = loader;
        if (result == null) {
            synchronized (this) {
                result = loader;
                if (result == null) {
                    loader = result = new Loader(EMPTY, this.getClass().getClassLoader());
                }
            }
        }
        return result;
    }

    private static class Loader implements LoaderInterface {
        private static final Logger log = Logger.getLogger(Loader.class);
        private static final long serialVersionUID = -8544724475469653381L;
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
