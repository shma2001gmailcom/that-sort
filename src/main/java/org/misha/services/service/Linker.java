package org.misha.services.service;

import org.misha.services.model.Data;
import org.misha.services.model.impl.PrintData;
import org.misha.services.model.impl.SearchData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        final Data<String> searchData = new SearchData("Result", "class");
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        final ServiceProvider provider = (ServiceProvider) context.getBean("provider");
        final String result = (String) provider.serviceFor(searchData).serve(searchData).getResult();
        final Data<String> printData = new PrintData(result);
        provider.serviceFor(printData).serve(printData);
    }
}
