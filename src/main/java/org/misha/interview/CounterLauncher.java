package org.misha.interview;

import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicLong;

/**
 * author: misha
 * date: 3/16/16
 * time: 11:13 AM
 */
public class CounterLauncher {
    private static final Logger log  = Logger.getLogger(CounterLauncher.class);

    public static void main(String... args) {
        final Counter counter = new MyCounter(new AtomicLong(0L));
        for(int i = 0; i < 20; ++i) {
           new Thread(new Worker(counter)).start();
        }
        log.debug(counter.currentValue());
    }
}
