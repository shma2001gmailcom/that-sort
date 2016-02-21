package org.misha.executors;
/**
 * author: misha
 * date: 8/24/15 10:48 PM.
 */

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;

public class TaskExecutorExample {

    @Inject
    private TaskExecutor taskExecutor;

    public TaskExecutorExample(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
    }

    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TaskExecutorExample printer = context.getBean(TaskExecutorExample.class);
        printer.printMessages();
    }

    public void printMessages() {
        for (int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private class MessagePrinterTask implements Runnable {
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            System.out.println(message);
        }
    }
}
