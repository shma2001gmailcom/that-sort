package org.misha.proxy.proxyjdk.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.proxyjdk.MyObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 5:56 PM
 */

public class ProxyService {
    private static final String PROXY_PACKAGE = "org.misha.proxy.proxyjdk.impl.";//todo: configure or inject
    private static final String REAL_PROXY = "RealProxy";//todo: configure or inject
    private static final Logger log = Logger.getLogger(ProxyService.class);

    private ProxyService() {
    }

    public static MyObject getProxy(final int count, final String name) {
        try {
            return getMyObject(PROXY_PACKAGE + REAL_PROXY, count, name);
        } catch (Exception e) {
            log.info(e.getMessage() + " Can't find proxyjdk. Shutting down.");
            System.exit(-1);
        }
        return null;
    }

    static MyObject getMyObject(
            final String proxyFullyQualifiedName, final Integer count, final String objName
    ) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
             InvocationTargetException {
        final Class<?> c = ProxyService.class.getClassLoader().loadClass(proxyFullyQualifiedName);
        if (c.getInterfaces()[0].equals(MyObject.class)) {
            final Constructor constructor = c.getDeclaredConstructor(Integer.class, String.class);
            constructor.setAccessible(true);
            return (MyObject) constructor.newInstance(count, objName);
        }
        throw new IllegalStateException(c.getName() + "is not a correct proxyjdk");
    }
}
