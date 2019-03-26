package org.misha.inject;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class App {
    public static void main(String... args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
        SingletonWithPrototype singleton = (SingletonWithPrototype) ctx.getBean("singleton");
        IntStream.range(0, 10).forEach(i -> {
            PrototypeInSingleton prototype = singleton.getPrototype(new ArrayList<>());
            System.out.println(ObjectUtils.identityToString(prototype));
            prototype.add(String.valueOf(i));
            System.out.println(prototype);
        });
    }
}

