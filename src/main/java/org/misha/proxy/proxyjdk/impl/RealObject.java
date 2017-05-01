package org.misha.proxy.proxyjdk.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.proxyjdk.MyObject;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:07 PM
 */
@SuppressWarnings("unused class")
public final class RealObject implements MyObject {
    private static final Logger log = Logger.getLogger(RealObject.class);
    private final int count;
    private final String name;

    private RealObject(final Integer times, final String name) {
        count = times;
        this.name = name;
    }

    @Override
    public void printCount() {
        log.info(count);
    }

    @Override
    public void printName(final int times) {
        int count = 0;
        while (count < times) {
            count++;
            log.info(name);
        }
    }
}
