package org.misha.services.service.impl;

import org.apache.log4j.Logger;
import org.misha.services.model.Data;
import org.misha.services.model.impl.SearchData;
import org.misha.services.model.impl.SearchResult;
import org.misha.services.service.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:34 PM
 */

@SuppressWarnings("UnusedDeclaration")//used through reflection
public final class SearchService implements Service<SearchResult> {
    private static final Logger log = Logger.getLogger(SearchService.class);

    private static void search(final File file, final List<String> found, final String toSearch) {
        final boolean condition = file.getAbsolutePath().contains(toSearch);
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            if (files != null) {
                for (final File f : files) {
                    search(f, found, toSearch);
                    log.info("...");
                }
            }
        } else {
            if (condition) {
                found.add(file.getAbsolutePath());
                log.info("something found ...");
            }
        }
    }

    @Override
    public SearchResult serve(final Data data) {
        log.info("search service is running.\n");
        final List<String> results = new ArrayList<String>();
        final File file = new File("./");
        search(file, results, ((SearchData) data).getCriteria());
        return new SearchResult(results.toString());
    }

    @Override
    public boolean isSuitableFor(final Class c) {
        return c.equals(SearchData.class);
    }
}
