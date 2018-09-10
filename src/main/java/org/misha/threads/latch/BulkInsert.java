package org.misha.threads.latch;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import static java.text.MessageFormat.format;

/**
 * author: misha
 * date: 11/27/16
 * time: 5:58 PM
 */
class BulkInsert implements Runnable {
    private static final int batchLimit = 10;
    private final List<String> cache;
    private final CountDownLatch latch;

    private BulkInsert(
            final List<String> cache, final CountDownLatch latch
    ) {
        this.cache = cache;
        this.latch = latch;
    }

    static BulkInsert createBulkInsert(
            final List<String> cache, final CountDownLatch latch
    ) {
        return new BulkInsert(cache, latch);
    }

    private void bulkInsert() {
        int counter = 0;
        for (final String row : cache) {
            ++counter;
            if ((counter % batchLimit) == 0) {
                //make something meaning here
                Csv.log.debug(format("{0}--execute batch{1}", counter, row));
            }
        }
        //make something meaning here
        Csv.log.trace(format("{0}--execute batch reminder", counter));
    }

    @Override
    public void run() {
        bulkInsert();
        latch.countDown();
    }
}
