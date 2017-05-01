package org.misha.proxy.proxyjdk;

import org.misha.proxy.proxyjdk.impl.ProxyService;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:11 PM
 */
public interface MyObject {//make abstract

    void printCount();

    void printName(int times);

    public static final class Factory {
        public static MyObject getInstance(final int count, final String name) {
            return ProxyService.getProxy(count, name);
        }
    }
}
