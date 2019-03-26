package org.misha.inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Named;
import java.util.List;
import java.util.function.Function;

@Configuration
public class BeanConfig {
    @Bean
    public Function<List<String>, PrototypeInSingleton> beanFactory() {
        return list -> prototype(list);
    }

    //@Bean
    //@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Named
    private PrototypeInSingleton prototype(List<String> list) {
        return new PrototypeInSingleton(list);
    }

    @Bean
    public SingletonWithPrototype singleton() {
        return new SingletonWithPrototype(beanFactory());
    }
}
