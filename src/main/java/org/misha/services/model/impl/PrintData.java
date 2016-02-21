package org.misha.services.model.impl;

import org.misha.services.model.Data;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:32 PM
 */

public final class PrintData implements Data<String> {
    private final String value;

    public PrintData(final String value) {
        this.value = value;
    }

    @Override
    public String getData() {
        return value;
    }
}
