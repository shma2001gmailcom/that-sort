package org.misha.threads.executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * author: misha
 * date: 3/13/16
 * time: 10:59 AM
 */
public class Launcher {
    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PrinterExecutor printer = context.getBean(PrinterExecutor.class);
        printer.printMessages();
    }
}
