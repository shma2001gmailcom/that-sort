package org.misha.runtime.annotation.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author: misha
 * date: 10/18/15 10:40 AM.
 */
abstract class MyInvocationHandler implements InvocationHandler {
    private final Object proxied;
    private final Class<? extends Annotation> annotation;

    MyInvocationHandler(final Object proxied, final Class<? extends Annotation> someClass) {
        this.proxied = proxied;
        this.annotation = someClass;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        Method m = proxied.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (m.isAnnotationPresent(annotation)) {
            return doMethod(args);
        }
        return method.invoke(proxied, args);
    }

    abstract Object doMethod(Object[] args);
}
