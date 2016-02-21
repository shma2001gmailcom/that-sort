package org.misha.services.service.impl;

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
public final class PrintService implements Service<PrintResult> {

    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    @Override
    public PrintResult serve(final Data data) {
        System.out.println("print service is running.");
        System.out.println(data.getData());
        return null;
    }

    @Override
    public boolean isSuitableFor(final Class c) {
        return c.equals(PrintData.class);
    }
}
