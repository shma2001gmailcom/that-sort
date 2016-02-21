package org.misha.services.service;

import org.misha.services.model.Data;
import org.misha.services.model.Result;
import org.misha.services.model.impl.PrintData;
import org.misha.services.model.impl.SearchData;

/**
 * Author: mshevelin
 * Date: 10/9/14
 * Time: 11:56 AM
 */

public final class Linker {

    private Linker() {
    }

    public static Linker getInstance() {
        return new Linker();
    }

    public void link() {
        final Data<String> searchData = new SearchData("Result", "Result");
        Service service = ServiceProvider.getInstance().getServiceFor(searchData);
        final Result toPrint = service.serve(searchData);
        final Data<String> printData = new PrintData((String) toPrint.getResult());
        service = ServiceProvider.getInstance().getServiceFor(printData);
        service.serve(printData);
    }
}
