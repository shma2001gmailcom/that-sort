package org.misha.beanutils.beans;

import java.util.Date;
import java.util.HashMap;

/**
 * author: misha
 * date: 5/3/16
 * time: 12:25 PM
 */
public enum Type {
    INTEGER(Integer.class),
    STRING(String.class),
    DATE(Date.class);

    private final Class type;
    private static HashMap<String, Type> map = new HashMap<String, Type>() {{
        put("Integer",INTEGER);
        put("String",STRING);
        put("Date", DATE);
    }};

    private Type(Class c) {
        type = c;
    }

    public Class getType() {
        return type;
    }

    public static Type getByName(final String typeName) {
        return map.get(typeName);
    }
}
