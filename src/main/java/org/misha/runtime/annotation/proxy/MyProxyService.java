package org.misha.runtime.annotation.proxy;

import org.misha.runtime.annotation.MyAble;
import org.misha.runtime.annotation.objects.My;

import javax.inject.Named;
import java.lang.reflect.Proxy;

/**
 * author: misha
 * date: 2/21/16 8:21 PM.
 */
@Named
public class MyProxyService {
    private static Object createProxy(final Object proxied, final Class<?> someInterface) {
        return Proxy.newProxyInstance(MyInvocationHandler.class.getClassLoader(), new Class[]{someInterface},
                new MyInvocationHandler(proxied, UseProxy.class) {
                    @Override
                    Object doMethod(final Object[] args) {
                        new My().print();
                        return null;
                    }
                }
        );
    }

    public MyAble getInstance() {
        return (MyAble) createProxy(new My(), MyAble.class);
    }
}
