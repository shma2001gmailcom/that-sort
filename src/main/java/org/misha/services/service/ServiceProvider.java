package org.misha.services.service;

import org.apache.log4j.Logger;
import org.misha.services.model.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:43 PM
 */

public final class ServiceProvider {
    private static final String servicesPackage = "org.misha.services.service.impl";
    private static final char DOT = '.';
    private static final String[] serviceNames = {"PrintService", "SearchService"};
    private static final List<Service> services = new ArrayList<Service>(2);
    private static final Logger log = Logger.getLogger(ServiceProvider.class);

    private ServiceProvider() {
        findServices();
    }

    public static ServiceProvider getInstance() {
        return new ServiceProvider();
    }

    private void findServices() {
        if (services.isEmpty()) {
            for (final String s : serviceNames) {
                try {
                    services.add(createService(servicesPackage + DOT + s));
                } catch (Exception e) {
                    log.info(e.getMessage() + " Can't find services. Shutting down.");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Service getServiceFor(final Data data) {
        for (final Service service : services) {
            if (service.isSuitableFor(data.getClass())) {
                return service;
            }
        }
        throw new IllegalStateException("can't find service for data type: '" + data.getClass());
    }

    private Service createService(final String name) throws ClassNotFoundException, IllegalAccessException,
                                                            InstantiationException {
        final Class<?> c = this.getClass().getClassLoader().loadClass(name);
        if (c.getInterfaces()[0].equals(Service.class)) {
            return (Service) c.newInstance();
        }
        throw new IllegalStateException(c.getName() + "is not a correct Service");
    }
}
