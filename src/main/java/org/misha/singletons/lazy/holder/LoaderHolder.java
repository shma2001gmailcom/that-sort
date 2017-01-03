package org.misha.singletons.lazy.holder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.misha.singletons.LoaderInterface;

import static java.lang.Thread.currentThread;

/**
 * author: misha
 * date: 1/21/17
 * time: 5:41 PM
 */
public class LoaderHolder {

    public static LoaderInterface getLoader() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final LoaderInterface INSTANCE = create();

        private static LoaderInterface create() {
            return new Loader(StringUtils.EMPTY, currentThread().getContextClassLoader());
        }
    }

    private static class Loader implements LoaderInterface {
        private static final Logger log = Logger.getLogger(Loader.class);
        private static final long serialVersionUID = -7662185562805986161L;
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
