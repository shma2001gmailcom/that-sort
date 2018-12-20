package org.misha.threads.buffer;

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
        return new BoundedBuffer(20);
    }

    @Bean
    ExecutorService executor() {
        return Executors.newFixedThreadPool(11);
    }
}
