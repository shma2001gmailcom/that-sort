package org.misha.services.model.impl;

import org.apache.commons.lang3.StringUtils;
import org.misha.services.model.Result;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 4:25 PM
 */

public final class SearchResult implements Result<String> {
    private final String result;

    public SearchResult(final String result) {
        this.result = result;
    }

    @Override
    public String getResult() {
        return StringUtils.isNotBlank(result) ? result : "None found.";
    }
}
