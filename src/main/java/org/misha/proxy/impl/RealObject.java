package org.misha.proxy.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.MyObject;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:07 PM
 */

public final class RealObject implements MyObject {
    private static final Logger log = Logger.getLogger(RealObject.class);
    private final int count;
    private final String name;

    protected RealObject(final int times, final String name) {
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
