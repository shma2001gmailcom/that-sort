package org.misha.luxoft;

import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Leslie Lamport's 'bakery' lock
 *
 * "The bakery algorithm was also where I introduced the idea of variables belonging to a process -- that is, variables
 * that could be read by multiple processes, but written by only a single process.  I was aware from the beginning
 * that such algorithms had simple distributed implementations, where the variable resides at the owning process, and
 * other processes read it by sending messages to the owner.  Thus, the bakery algorithm marked the beginning of my
 * study of distributed algorithms."
 * see </https://lamport.azurewebsites.net/pubs/bakery.pdf>
 */
class Lamport {
    private final int threadCount;
    private final AtomicIntegerArray ticket;
    private final AtomicIntegerArray entering;//0 = new client; 1 = has a ticket

    Lamport(final int threadCount) {
        this.threadCount = threadCount;
        ticket = new AtomicIntegerArray(threadCount);
        entering = new AtomicIntegerArray(threadCount);
    }

    void lock(int pid) {
        entering.set(pid, 1);//a new client entered
        ticket.set(pid, nextTicket());//since ticket has been received
        entering.set(pid, 0);//the client no more is new
        for (int i = 0; i < ticket.length(); ++i) {
            if (i != pid) {//check if the client is eligible to start trading
                while (entering.get(i) == 1) Thread.yield();//wait until new clients are entering
                while (ticket.get(i) != 0 && (ticket.get(i) < ticket.get(pid) ||
                        (ticket.get(i) == ticket.get(pid) && i < pid))) Thread.yield();
                //wait until other existing clients, including those with the same ticket but less pid, have finished
                //their trade
            }
        }
    }

    private int nextTicket() {
        int max = 0;
        for (int i = 0; i < threadCount; i++) {
            int current = ticket.get(i);
            if (current > max) {
                max = current;
            }
        }
        return 1 + max;
    }

    void unlock(int pid) {
        ticket.set(pid, 0);
    }

    static class MyRunnable implements Runnable {
        private final int pid;
        private final Lamport lock;
        private final List<Integer> shared;

        MyRunnable(final int pid, final Lamport lock, List<Integer> shared) {
            this.pid = pid;
            this.lock = lock;
            this.shared = shared;
        }

        @Override
        public void run() {
            while (true) {
                if (lock != null) lock.lock(pid);
                int j = shared.get(0);
                ++j;
                if (j > 1_000_000) break;
                shared.set(0, j);
                System.err.println(pid + " incremented " + shared.get(0));
                if (lock != null) lock.unlock(pid);
            }
            System.out.println(pid + ": " + shared.get(0));
        }
    }
}
