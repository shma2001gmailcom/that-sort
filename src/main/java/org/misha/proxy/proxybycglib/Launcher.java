package org.misha.proxy.proxybycglib;

import net.sf.cglib.proxy.Enhancer;
import org.apache.log4j.Logger;
import org.misha.proxy.proxybycglib.impl.Interceptor;
import org.misha.proxy.proxybycglib.impl.RealObject;
import org.misha.proxy.proxyjdk.MyObject;

/**
 * author: misha
 * date: 5/1/17
 * time: 2:03 PM
 */
public class Launcher {

    public static void main(String... args) {
        final Enhancer e = new Enhancer();
        e.setSuperclass(RealObject.class);
        e.setCallback(new Interceptor(Logger.getLogger(Launcher.class),
                                      e.getClass().getName() + " extends " + e.getClass().getGenericSuperclass()
        ));
        final MyObject myObject =
                (MyObject) e.create(new Class[]{Integer.class, String.class}, new Object[]{2, "basil"});
        myObject.printCount();
        myObject.printName(2);
    }
}
