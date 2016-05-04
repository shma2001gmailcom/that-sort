package org.misha.beanutils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * author: misha
 * date: 5/3/16
 * time: 3:53 PM
 */
public class Data {
    private String type;
    private String value;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "type: "+ type + (value != null ? " value: " + value: EMPTY);
    }
}
