package org.misha.proxy.proxybycglib.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.proxyjdk.MyObject;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:07 PM
 */
@SuppressWarnings("unused class")
public class RealObject implements MyObject {
    private static final Logger log = Logger.getLogger(RealObject.class);
    private final int count;
    private final String name;

    RealObject(final Integer times, final String name) {
        count = times;
        this.name = name;
    }

    @Override
    public void printCount() {
        log.info(count);
    }

    @Override
    public void printName(final int times) {
        int c = 0;
        while (c < times) {
            c++;
            log.info(name);
        }
    }
}
