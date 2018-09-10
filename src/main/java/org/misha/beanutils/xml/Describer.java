package org.misha.beanutils.xml;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

    private static Object fieldValue(Object bean, Field field) {
        try {
            return field.get(bean);
        } catch (IllegalAccessException ignored) {
            log.error(ignored.getMessage());
        }
        return null;
    }

    public String describe(Object bean, int depth) throws ReflectiveOperationException {
        StringBuilder sb = new StringBuilder(tab(depth));
        sb.append(getSimpleName(bean)).append("[\n");
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.getName().contains("$")) {
                return sb.toString();
            } else {
                field.setAccessible(true);
            }
            Object value = fieldValue(bean, field);
            if (value == null) {
                value = "null";
            }
            ++depth;
            if (value.getClass().getCanonicalName().contains(beansPackage)) {
                if (value instanceof Enum) {
                    sb = forEnum((Enum) bean, sb, (Enum) value);
                } else {
                    sb.append(describe(value, depth));
                }
            } else if (value instanceof Collection) {
                sb.append(tab(depth)).append(value.getClass().getSimpleName()).append("{\n");
                sb = forCollection(depth, sb, (Collection) value);
                sb.append(tab(depth)).append("}\n");
            } else if (value instanceof Object[]) {
                sb.append(tab(depth)).append(value.getClass().getSimpleName()).append("{\n");
                sb = forArray(depth, sb, (Object[]) value);
                sb.append(tab(depth)).append("}\n");
            } else {
                sb.append(tab(depth)).append(getSimpleName(value)).append(": ").append(value).append("\n")
                  .append(tab(depth - 1)).append("]\n");
            }
            --depth;
        }
        return sb.toString().replaceAll("\\n\\n", "\n");
    }

    private StringBuilder forArray(
            int depth, StringBuilder sb, Object[] value
    ) throws ReflectiveOperationException {
        List collection = Arrays.asList(value);
        for (Iterator it = collection.iterator(); it.hasNext(); ) {
            Object element = it.next();
            StringBuilder description = sb.append(describe(element, depth + 1));
            sb = it.hasNext() ? description : description.append("\n");
        }
        return sb;
    }

    private StringBuilder forEnum(
            Enum bean, StringBuilder sb, Enum value
    ) {
        if (bean.name().equals(value.name())) {
            sb.delete(sb.length() - 2, sb.length()).append(": ").append(value.name()).append('\n');
        }
        return sb;
    }

    private StringBuilder forCollection(
            int depth, StringBuilder sb, Collection value
    ) throws ReflectiveOperationException {
        for (Iterator it = value.iterator(); it.hasNext(); ) {
            Object element = it.next();
            StringBuilder description = sb.append(describe(element, depth + 1));
            sb = it.hasNext() ? description : description.append("\n");
        }
        return sb;
    }

    private String getSimpleName(Object value) {
        return value.getClass().getSimpleName();
    }

    private String tab(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2 * length; ++i) {
            sb.append("-");
        }
        return sb.toString();
    }
}
