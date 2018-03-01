package org.misha.threads.latch;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import static java.text.MessageFormat.format;

/**
 * Author: mshevelin
 * Date: 8/12/13
 * Time: 6:25 PM
 * <p/>
 * class parses csv file, makes db calls, retrieves results and writes it to log file
 */
public final class Csv {
    static final Logger log = Logger.getLogger(Csv.class);
    private static final int totalSlots = 200;
    private final String filePath;
    private volatile int rowCount;

    private Csv(final String filePath) {
        this.filePath = filePath;
    }

    private static String parseRow(final String row) {
        return row + " parsed.";
    }//stub

    public static void main(final String[] args) {
        log.debug(new File("./").getAbsolutePath());
        final Csv csv = new Csv("../../logs/lie.log");
        csv.convert();
    }

    private void convert() {
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
            log.error(format("{0} necessary file: {1} is not found, program is shutting down.", e.getMessage(),
                             filePath
            ));
            throw new RuntimeException(e);
        }
        return sc;
    }
}

