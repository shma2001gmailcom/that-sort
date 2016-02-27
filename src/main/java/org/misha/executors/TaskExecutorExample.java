package org.misha.executors;
/**
 * author: misha
 * date: 8/24/15 10:48 PM.
 */

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;

public class TaskExecutorExample {
    private static final Logger log = Logger.getLogger(TaskExecutorExample.class);
    public static final int INT = 6;

    @Inject
    private TaskExecutor taskExecutor;

    public TaskExecutorExample(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TaskExecutorExample printer = context.getBean(TaskExecutorExample.class);
        printer.printMessages();
    }

    public void printMessages() {
        for (int i = 0; i < INT; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private class MessagePrinterTask implements Runnable {
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            log.info(message);
        }
    }
}
