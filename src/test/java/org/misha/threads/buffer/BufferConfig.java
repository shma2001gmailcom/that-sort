package org.misha.threads.buffer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = {"org.misha.threads"})
public class BufferConfig {

    @Bean
    public BoundedBuffer boundedBuffer() {
        return new BoundedBuffer(5);
    }

    @Bean
    @Qualifier("producers")
    ExecutorService produsers() {
        return Executors.newFixedThreadPool(5);
    }

    @Bean
    @Qualifier("consumers")
    ExecutorService consumers() {
        return Executors.newFixedThreadPool(5);
    }
}
