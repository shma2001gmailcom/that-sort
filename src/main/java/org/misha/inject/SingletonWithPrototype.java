package org.misha.inject;


import javax.inject.Named;
import java.util.List;
import java.util.function.Function;

@Named
public class SingletonWithPrototype {
    private final Function<List<String>, PrototypeInSingleton> beanFactory;

    SingletonWithPrototype(final Function<List<String>, PrototypeInSingleton> beanFactory) {
        this.beanFactory = beanFactory;
    }

    PrototypeInSingleton getPrototype(List<String> list) {
        return beanFactory.apply(list);
    }
}
