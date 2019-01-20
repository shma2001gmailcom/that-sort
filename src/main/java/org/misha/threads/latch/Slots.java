package org.misha.threads.latch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.text.MessageFormat.format;

/**
 * author: misha
 * date: 11/27/16
 * time: 5:56 PM
 */
class Slots implements Runnable {
    private final int slotCount;
    private final CountDownLatch waitForInsert;
    private final int rowCount;
    private final List<String> cache;

    private Slots(
            final int slotCount, final CountDownLatch latch, final int rowCount, final List<String> cache
    ) {
        this.slotCount = slotCount;
        waitForInsert = latch;
        this.rowCount = rowCount;
        this.cache = cache;
    }

    static Slots createSlots(
            final int slotCount, final CountDownLatch latch1, final int rowCount, final List<String> cache
    ) {
        return new Slots(slotCount, latch1, rowCount, cache);
    }

    private void convertSlots() {
        assert rowCount >= slotCount : "row count should be not less than slot count.";
        final int rowsPerSlot = rowCount / slotCount;
        final int residue = rowCount % slotCount;
        final CountDownLatch latch = new CountDownLatch((residue == 0) ? slotCount : (1 + slotCount));
        final Thread logThread = new Thread(LogWriter.createLogWriter(latch));
        logThread.start();
        for (int i = 0; i < slotCount; ++i) {
            final Thread oneSlotThread =
                    new Thread(Slot.createSlot(cache, i, rowsPerSlot, latch), format("doSlot#{0}", i));
            oneSlotThread.start();
        }
        if (residue > 0) {
            final Thread residueSlotThread = new Thread(
                    Slot.createSlot(cache, 1 + slotCount, residue, latch),
                    format("doSlot#{0}", 1 + slotCount)
            );
            residueSlotThread.start();
        }
    }

    @Override
    public void run() {
        try {
            waitForInsert.await();
            convertSlots();
        } catch (final InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}
