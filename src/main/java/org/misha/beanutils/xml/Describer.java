package org.misha.beanutils.xml;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;

/**
 * author: misha
 * date: 5/4/16
 * time: 9:44 PM
 */
public class Describer {
    private static final Logger log = Logger.getLogger(Describer.class);
    private final String beansPackage;

    public Describer(String s) {
        beansPackage = s;
    }

    public String describe(Object bean, int depth) throws IllegalAccessException, NoSuchMethodException,
                                                   InvocationTargetException {
        StringBuilder sb = new StringBuilder(tab(depth));
        sb = sb.append(getSimpleName(bean)).append("[\n");
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = fieldValue(bean, field);
            if (value == null) {
                value = "null";
            }
            ++depth;
            if (value.getClass().getCanonicalName().contains(beansPackage)) {
                if (value instanceof Enum) {
                    Enum e = (Enum) value;
                    sb = sb.append(tab(depth))
                           .append(Enum.valueOf(e.getDeclaringClass(), e.name()))
                           .append(e.ordinal()).append("\n").append(tab(depth)).append("\n");
                } else {
                    sb = sb.append(describe(value, depth));
                }
            } else if (value instanceof Collection) {
                sb = sb.append(tab(depth)).append(value.getClass().getSimpleName()).append("{\n");
                Collection collection = (Collection) value;
                for (Iterator it = collection.iterator(); it.hasNext(); ) {
                    Object element = it.next();
                    StringBuilder description = sb.append(describe(element, depth + 1));
                    sb = it.hasNext()? description : description.append("\n");
                }
                sb = sb.append(tab(depth)).append("}\n");
            } else {
                sb = sb.append(tab(depth)).append(getSimpleName(value)).append(": ").append(value).append("\n")
                       .append(tab(depth - 1)).append("]\n");
            }
            --depth;
        }
        return sb.toString().replaceAll("\\n\\n", "\n");
    }

    private String getSimpleName(Object value) {
        return value == "null" ? "NULL" : value.getClass().getSimpleName();
    }

    private String tab(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2 * length; ++i) {
            sb = sb.append("-");
        }
        return sb.toString();
    }

    private static Object fieldValue(Object bean, Field field) {
        try {
            return field.get(bean);
        } catch (IllegalAccessException ignored) {
            log.error(ignored.getMessage());
        }
        return null;
    }
}
