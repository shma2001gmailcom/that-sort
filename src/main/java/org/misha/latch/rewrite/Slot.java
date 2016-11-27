package org.misha.latch.rewrite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.text.MessageFormat.format;

/**
 * author: misha
 * date: 11/27/16
 * time: 5:57 PM
 */
class Slot implements Runnable {
    private final CountDownLatch latch;
    private final List<String> slotCache = new ArrayList<String>();
    private final List<String> cache;
    private final int from;
    private final int to;

    private Slot(final List<String> cache,
                 final int slotNumber,
                 final int rowsPerSlot,
                 final CountDownLatch latch
    ) {
        this.latch = latch;
        this.cache = cache;
        int size = cache.size();
        from = ((slotNumber * rowsPerSlot) <= size) ? (slotNumber * rowsPerSlot)
                                                    : (slotNumber * (size / slotNumber));
        to = ((from + rowsPerSlot) <= size) ? (from + rowsPerSlot) : size;
    }

    static Slot createSlot(final List<String> cache,
                           final int slotNumber,
                           final int rowsPerSlot,
                           final CountDownLatch latch
    ) {
        final Slot result = new Slot(cache, slotNumber, rowsPerSlot, latch);
        result.addRowsToSlot();
        return result;
    }

    private void addRowsToSlot() {
        slotCache.addAll(cache.subList(from, to));
    }

    private void doSlot() {
        final File file = new File(format("./answer/{0}-{1}", from, to));
        Writer writer = null;
        try {
            writer = new FileWriter(file, false);
            for (final String record : slotCache) {
                writer.write(record + "\n");
                writer.flush();
            }
        } catch (final IOException ignored) {
            //e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    Csv.log.error(e);
                }
            }
        }
    }

    @Override
    public void run() {
        doSlot();
        latch.countDown();
    }
}
