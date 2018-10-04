package org.misha.proxy.proxybycglib.impl;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;
import org.misha.merge.Merge;
import org.misha.proxy.proxyjdk.MyObject;

import java.lang.reflect.Method;

/**
 * author: misha
 * date: 5/1/17
 * time: 1:51 PM
 */
public class Interceptor implements MethodInterceptor {
    private final Logger log;
    private final String service;

    public Interceptor(final Logger log, final String text) {
        this.log = log;
        this.service = text;
    }

    public Object intercept(final Object target,
                            final Method method,
                            final Object[] args,
                            final MethodProxy proxy
    ) throws Throwable {
        log.debug(service + " before " + method.getName());
        Merge.main(new String[]{});
        MyObject myObject = (MyObject) proxy.invokeSuper(target, args);
        log.debug(service + " after " + method.getName());
        return myObject;
    }
}
