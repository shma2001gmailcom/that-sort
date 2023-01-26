package org.misha.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Configuration
class Config implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = Logger.getLogger(Config.class);
    private final ConnectableObservable<Object> observable =
            Observable.<Object>create(subscriber -> {
                log.info("Starting");

            }).publish();

    @Bean
    public Observable<Object> observable() {
        return observable;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Connecting");
        observable.connect();
    }
}


@Component
class Foo {
    private static final Logger log = Logger.getLogger(Config.class);

    @Autowired
    public Foo(Observable<Object> tweets) {
        tweets.subscribe(status -> {
            log.info(status.toString());
        });
        log.info("Subscribed");
    }
}

@Component
class Bar {
    private static final Logger log = Logger.getLogger(Config.class);

    @Autowired
    public Bar(Observable<Object> tweets) {
        tweets.subscribe(status -> {
            log.info(status.toString());
        });
        log.info("Subscribed");
    }
}

