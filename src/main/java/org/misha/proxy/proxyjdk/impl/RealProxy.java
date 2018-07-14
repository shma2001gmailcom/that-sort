package org.misha.proxy.proxyjdk.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.proxyjdk.MyObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.misha.proxy.proxyjdk.impl.ProxyService.getMyObject;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:12 PM
 */
@SuppressWarnings("UnusedDeclaration")
public final class RealProxy implements MyObject {
    private static final Logger log = Logger.getLogger(RealProxy.class);
    private final MyObject proxy;

    @SuppressWarnings("unused")
    private RealProxy(final Integer times, final String name) {
        proxy = createProxy(times, name);
    }

    @Override
    public void printCount() {
        log.info("proxyjdk add-on:-------------------before count");
        proxy.printCount();
        log.info("proxyjdk add-on:-------------------after count");
    }

    @Override
    public void printName(final int times) {
        if (times > 1) {//wrapping rule
            log.info("proxyjdk add-on:-------------------before name");
            proxy.printName(times);
            log.info("proxyjdk add-on:-------------------after name");
        } else {
            log.info("proxyjdk add-on: need more times");
        }
    }

    private MyObject createProxy(final int times, final String name) {
        try {
            return (MyObject) Proxy.newProxyInstance(MyObject.class.getClassLoader(),//loader
                                                     new Class<?>[]{MyObject.class},//interfaces
                                                     new RealInvocationHandler(
                                                             getMyObject("org.misha.proxy.proxyjdk.impl.RealObject",
                                                                         times,
                                                                         name
                                                             ))//handler
            );
        } catch (Exception e) {
            throw new IllegalStateException("unable instantiate");
        }
    }

    static class RealInvocationHandler implements InvocationHandler {
        private final MyObject impl;

        private RealInvocationHandler(final MyObject impl) {
            this.impl = impl;
        }

        @Override
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            return method.invoke(impl, args);
        }
    }
}
