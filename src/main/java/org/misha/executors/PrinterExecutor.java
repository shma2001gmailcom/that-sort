package org.misha.executors;
/**
 * author: misha
 * date: 8/24/15 10:48 PM.
 */

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.inject.Inject;

public class PrinterExecutor {
    private static final Logger log = Logger.getLogger(PrinterExecutor.class);
    public static final int INT = 6;

    @Inject
    private TaskExecutor taskExecutor;

    public PrinterExecutor(ThreadPoolTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }



    public void printMessages() {
        for (int i = 0; i < INT; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private static class MessagePrinterTask implements Runnable {
        private String message;

        public MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            log.info(message);
        }
    }
}
