package org.misha.services.service;

import org.apache.log4j.Logger;
import org.misha.services.model.Data;

import javax.inject.Named;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static java.text.MessageFormat.format;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:43 PM
 */
@Named("provider")
final class ServiceProvider {
    private static final String servicesPackage = "org.misha.services.service.impl";
    private static final char DOT = '.';
    private static final List<Service> services = new ArrayList<Service>();
    private static final Logger log = Logger.getLogger(ServiceProvider.class);
    private static final String FATAL_ERROR = "Finding or instantiating services are impossible. Shutting down.";
    private static final String SERVICE_NOT_FOUND = "can''t find service for data type: ''{0}";
    private static final String INCORRECT_SERVICE = "is not a correct service";
    private static final String servicesList = "PrintService,SearchService";

    private ServiceProvider() {
        findServices();
    }

    private void findServices() {
        log.debug("\n\n\n" + servicesList);
        String[] serviceNames = servicesList.split(",");
        if (services.isEmpty()) {
            for (final String name : serviceNames) {
                try {
                    services.add(createService(format("{0}{1}{2}", servicesPackage, DOT, name)));
                } catch (Exception e) {
                    log.info(format("{0}  " + FATAL_ERROR, e.getMessage()));
                    throw new RuntimeException(e);
                }
            }
        }
    }

    Service serviceFor(final Data data) {
        for (final Service service : services) {
            if (service.isSuitableFor(data.getClass())) {
                return service;
            }
        }
        throw new IllegalStateException(
                format(SERVICE_NOT_FOUND, data.getClass()));
    }

    private Service createService(final String name) throws ClassNotFoundException, IllegalAccessException,
                                                            InstantiationException, NoSuchMethodException,
                                                            InvocationTargetException {
        final Class<?> c = this.getClass().getClassLoader().loadClass(name);
        if (c.getInterfaces()[0].equals(Service.class)) {
            final Constructor<?> constructor = c.getDeclaredConstructor();
            constructor.setAccessible(true);
            return (Service) constructor.newInstance();
        }
        throw new IllegalStateException(format("{0} " + INCORRECT_SERVICE, c.getName()));
    }
}
