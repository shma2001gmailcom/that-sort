package org.misha.runtime.annotation;

import org.apache.log4j.Logger;
import org.misha.runtime.annotation.proxy.MyProxyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author: misha
 * date: 2/21/16 1:58 PM.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyAble my1 = context.getBean(MyProxyService.class).getInstance();
        my1.print();
        log.info(my1);
        MyAble my2 = context.getBean(MyProxyService.class).getInstance();
        my2.print();
        log.info(my2);
    }
}
