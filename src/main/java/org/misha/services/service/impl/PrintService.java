package org.misha.services.service.impl;

import org.apache.log4j.Logger;
import org.misha.services.model.Data;
import org.misha.services.model.impl.PrintData;
import org.misha.services.model.impl.PrintResult;
import org.misha.services.service.Service;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:38 PM
 */

@SuppressWarnings("UnusedDeclaration")//used through reflection
final class PrintService implements Service<PrintResult> {
    private static final Logger log = Logger.getLogger(PrintService.class);

    @Override
    public PrintResult serve(final Data data) {
        log.info("print service is running.");
        log.info(data.getData());
        return null;
    }

    @Override
    public boolean isSuitableFor(final Class c) {
        return c.equals(PrintData.class);
    }
}
