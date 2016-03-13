package org.misha.latch.rewrite;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * Author: mshevelin
 * Date: 8/12/13
 * Time: 6:25 PM
 * <p/>
 * class parses csv file, makes db calls, retrieves results and writes it to log file
 */
public final class Csv {
    private static final Logger log = Logger.getLogger(Csv.class);
    private static final int batchLimit = 10;
    private static final int totalSlots = 200;
    private final String filePath;
    private volatile int rowCount;

    public Csv(final String filePath) {
        this.filePath = filePath;
    }

    private static String parseRow(final String row) {
        return row + " parsed.";
    }//stub

    public static void main(final String[] args) {
        log.debug(new File("./").getAbsolutePath());
        final Csv csv = new Csv("../lie.log");
        csv.convert();
    }

    public void convert() {
        doRows();
    }

    private void doRows() {
        log.info("in do rows");
        final CountDownLatch waitForInsert = new CountDownLatch(1);
        final List<String> cache = initCache();
        final Thread insertThread = new Thread(BulkInsert.createBulkInsert(cache, waitForInsert));
        insertThread.start();
        final Thread slotsThread = new Thread(Slots.createSlots(totalSlots, waitForInsert, rowCount, cache));
        slotsThread.start();
    }

    private List<String> initCache() {
        final Scanner sc = getScanner();
        int count = 0;
        final List<String> cache = new ArrayList<String>();
        try {
            while (sc.hasNextLine()) {
                ++count;
                final String row = sc.nextLine();
                if (StringUtils.isEmpty(row)) {
                    continue;
                }
                final String parsed = parseRow(row);
                if (parsed != null) {
                    cache.add(parsed);
                }
            }
            rowCount = count;
        } catch (final IllegalArgumentException e) {
            log.error(e);
            throw new RuntimeException(e);
        } finally {
            sc.close();
        }
        return cache;
    }

    private Scanner getScanner() {
        Scanner sc;
        try {
            sc = new Scanner(new File(filePath));
        } catch (final FileNotFoundException e) {
            log.error(e.getMessage() + " necessary file: " + filePath + " is not found, program is shutting down."
            );
            throw new RuntimeException(e);
        }
        return sc;
    }

    private static class Slots implements Runnable {
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

        private static Slots createSlots(
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
                final Thread oneSlotThread = new Thread(Slot.createSlot(cache, i, rowsPerSlot, latch), "doSlot#" + i);
                oneSlotThread.start();
            }
            if (residue > 0) {
                final Thread residueSlotThread =
                        new Thread(Slot.createSlot(cache, 1 + slotCount, residue, latch), "doSlot#" + (1 + slotCount));
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

    private static class Slot implements Runnable {
        private final CountDownLatch latch;
        private final List<String> slotCache = new ArrayList<String>();
        private final List<String> cache;
        private final int size;
        private final int from;
        private final int to;

        private Slot(
                final List<String> cache, final int slotNumber, final int rowsPerSlot, final CountDownLatch latch
        ) {
            this.latch = latch;
            this.cache = cache;
            size = cache.size();
            from = ((slotNumber * rowsPerSlot) <= size) ? (slotNumber * rowsPerSlot)
                                                        : (slotNumber * (size / slotNumber));
            to = ((from + rowsPerSlot) <= size) ? (from + rowsPerSlot) : size;
        }

        private static Slot createSlot(
                final List<String> cache, final int slotNumber, final int rowsPerSlot, final CountDownLatch latch
        ) {
            final Slot result = new Slot(cache, slotNumber, rowsPerSlot, latch);
            result.addRowsToSlot();
            return result;
        }

        private void addRowsToSlot() {
            slotCache.addAll(cache.subList(from, to));
        }

        private void doSlot() {
            final File file = new File("./answer/" + from + "-" + to);
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
                        log.error(e);
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

    private static class LogWriter implements Runnable {
        private final CountDownLatch latch;

        private LogWriter(final CountDownLatch latch) {
            this.latch = latch;
        }

        public static LogWriter createLogWriter(final CountDownLatch latch) {
            return new LogWriter(latch);
        }

        private static void writeLogs() {
            //make something meaning here
            log.error("writing logs");
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            log.info("latch is open");
            writeLogs();
        }
    }

    private static class BulkInsert implements Runnable {
        private final List<String> cache;
        private final CountDownLatch latch;

        private BulkInsert(
                final List<String> cache, final CountDownLatch latch
        ) {
            this.cache = cache;
            this.latch = latch;
        }

        private static BulkInsert createBulkInsert(
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
                    log.debug(counter + "--execute batch" + row);
                }
            }
            //make something meaning here
            log.trace(counter + "--execute batch reminder");
        }

        @Override
        public void run() {
            bulkInsert();
            latch.countDown();
        }
    }
}

