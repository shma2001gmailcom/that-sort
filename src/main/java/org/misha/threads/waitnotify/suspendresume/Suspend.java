package org.misha.threads.waitnotify.suspendresume;

import org.apache.log4j.Logger;

class Suspend implements Runnable {
    protected String name;
    protected Thread t;
    private boolean suspendFlag;
    private static final Logger log = Logger.getLogger("Suspend");

    Suspend(String threadName) {
        name = threadName;
        t = new Thread(this, name);
        log.error("new thread: " + t);
        suspendFlag = false;
        t.start();
    }

    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                log.error(name + ": " + i + "  do job");
                Thread.sleep(200);
                if (suspendFlag) {
                    synchronized (this) {
                        while (suspendFlag) {
                            wait();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(name + " break");
        }
        log.error(name + " finish");
    }

    void mySuspend() {
        suspendFlag = true;
    }

    synchronized void myResume() {
        suspendFlag = false;
        notifyAll();
    }
}

class SuspendResume {
    private static final Logger log = Logger.getLogger("Suspend");

    public static void main(String... args) throws InterruptedException {
        Suspend ob1 = new Suspend("1");
        Suspend ob2 = new Suspend("2");
        try {
            Thread.sleep(1000);
            ob1.mySuspend();
            log.info("Suspend 1");
            Thread.sleep(1000);
            ob1.myResume();
            log.error("Awoke 1");
            ob2.mySuspend();
            log.error("Suspend 2");
            Thread.sleep(1000);
            ob2.myResume();
            log.error("Awoke 2");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Main break");
            try {// ожидание завершения потоков
                log.error("Wait for finish");
                ob1.t.join();
                ob2.t.join();
            } catch (Exception ex) {
                log.error("Main break error");
            }
            log.error("Main finish");
        }
    }
}


