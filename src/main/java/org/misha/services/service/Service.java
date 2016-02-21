package org.misha.services.service;

import org.misha.services.model.Data;
import org.misha.services.model.Result;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:27 PM
 */
public interface Service<T extends Result> {

    T serve(Data data);

    boolean isSuitableFor(Class<Data<?>> c);
}
