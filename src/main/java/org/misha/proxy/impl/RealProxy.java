package org.misha.proxy.impl;

import org.apache.log4j.Logger;
import org.misha.proxy.MyObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:12 PM
 */
@SuppressWarnings("UnusedDeclaration")//used through reflection)
public final class RealProxy implements MyObject {
    private static final Logger log = Logger.getLogger(RealProxy.class);
    private final MyObject proxy;

    public RealProxy(final Integer times, final String name) {
        proxy = createProxy(times, name);
    }

    @Override
    public void printCount() {
        System.out.println("proxy add-on:-------------------before count");
        proxy.printCount();
        System.out.println("proxy add-on:-------------------after count");
    }

    @Override
    public void printName(final int times) {
        if (times > 1) {//wrapping rule
            System.out.println("proxy add-on:-------------------before name");
            proxy.printName(times);
            System.out.println("proxy add-on:-------------------after name");
        } else {
            System.out.println("proxy add-on: need more times");
        }
    }

    private MyObject createProxy(final int times, final String name) {
        return (MyObject) Proxy.newProxyInstance(MyObject.class.getClassLoader(),//loader
                                                 new Class<?>[]{MyObject.class},//interfaces
                                                 new RealInvocationHandler(new RealObject(times, name))//handler
        );
    }

    private static class RealInvocationHandler implements InvocationHandler {
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
