package org.misha.executors;
/**
 * author: misha
 * date: 8/24/15 10:48 PM.
 */

import org.apache.log4j.Logger;
import org.springframework.core.task.TaskExecutor;

import javax.inject.Inject;

public class PrinterExecutor {
    private static final int INT = 6;
    private static final Logger log = Logger.getLogger(PrinterExecutor.class);
    private final TaskExecutor taskExecutor;

    @Inject
    public PrinterExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;

    }

    void printMessages() {
        for (int i = 0; i < INT; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }

    private static class MessagePrinterTask implements Runnable {
        private String message;

        MessagePrinterTask(String message) {
            this.message = message;
        }

        public void run() {
            log.info(message);
        }
    }
}
