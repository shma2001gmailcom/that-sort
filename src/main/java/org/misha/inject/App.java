package org.misha.inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.stream.IntStream;

class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        SingletonWithPrototype singleton = (SingletonWithPrototype) ctx.getBean("singleton");
        IntStream.range(0, 10).forEach(i -> {
            PrototypeInSingleton prototype = singleton.getPrototype(new ArrayList<>());
            log.debug(ObjectUtils.identityToString(prototype));
            prototype.add(String.valueOf(i), s -> String.valueOf((char) (2 + i << 6)));
            log.debug(prototype.toString());
        });
    }
}

