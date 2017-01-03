package org.misha.singletons.enums;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.misha.singletons.LoaderInterface;

/**
 * author: misha
 * date: 1/21/17
 * time: 5:56 PM
 */
public enum LoaderEnum {
    LOADER;

    private final LoaderInterface loader;

    LoaderEnum() {
        loader = create();
    }

    public LoaderInterface getLoader() {
        return loader;
    }

    private LoaderInterface create() {
        return new Loader(StringUtils.EMPTY, this.getClass().getClassLoader().getParent());
    }

    private static class Loader implements LoaderInterface {
        private static final Logger log = Logger.getLogger(Loader.class);
        private static final long serialVersionUID = 6320263098639451712L;
        private final String name;
        private final transient ClassLoader classloader;

        private Loader(final String name, final ClassLoader classloader) {
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
