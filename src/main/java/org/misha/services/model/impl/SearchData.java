package org.misha.services.model.impl;

import org.misha.services.model.Data;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:30 PM
 */
public final class SearchData implements Data<String> {
    private final String name;
    private final String criteria;

    public SearchData(final String name, final String criteria) {
        this.name = name;
        this.criteria = criteria;
    }

    @Override
    public String getData() {
        return name;
    }

    public String getCriteria() {
        return criteria;
    }
}
