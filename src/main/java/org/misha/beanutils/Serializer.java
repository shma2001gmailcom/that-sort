package org.misha.beanutils;

import org.misha.beanutils.beans.Root;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * author: misha
 * date: 29.04.2016.
 */
public final class Serializer {
    private static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>";
    private static final String LESS_THAN = "<";
    private static final String GREATER_THAN = ">";
    private static final String LESS_THAN_SLASH = "</";
    private static final HashSet<Class> classes = new HashSet<>();

    static {
        classes.add(String.class);
        classes.add(BigDecimal.class);
        classes.add(Boolean.class);
        classes.add(GregorianCalendar.class);
    }

    private Serializer() {
    }

    public static String serialize(final Object bean) {
        return XML_HEADER + describe(bean);
    }

    private static String describe(final Object bean) {
        if (bean == null) {
            return "<>null</>";
        }
        if (bean instanceof String) {
            return (String) bean;
        }
        if (bean instanceof Enum) {
            final Enum e = (Enum) bean;
            return e.getDeclaringClass().getName() + ":" + e.name();
        }
        StringBuilder sb = new StringBuilder(LESS_THAN).append(bean.getClass().getSimpleName()).append(GREATER_THAN);
        sb = describeFields(bean, sb);
        sb = closeTag(sb, bean.getClass().getSimpleName());
        return sb.toString();
    }

    private static Object valueForLeaf(Object value, String name) {
        return value instanceof Calendar ? calendarString((GregorianCalendar) value, name) : value;
    }

    private static StringBuilder closeTag(StringBuilder sb, String name) {
        return sb.append(LESS_THAN_SLASH).append(name).append(GREATER_THAN);
    }

    private static StringBuilder openTag(StringBuilder sb, String name) {
        return sb.append(LESS_THAN).append(name).append(GREATER_THAN);
    }

    private static StringBuilder describeFields(final Object bean, StringBuilder sb) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(bean);
            } catch (IllegalAccessException e) {
                sb = sb.append("<INACCESSIBLE FIELD/>");
            }
            final String type = field.getType().getSimpleName();
            if (value != null) {
                if (value instanceof Collection) {
                    sb = sb.append("<List>");
                    final Object[] objects = ((Collection) value).toArray();
                    for (Object o : objects) {
                        sb = sb.append(describe(o));
                    }
                    sb = sb.append("</List>");
                } else if (needToBeDrawnAsIs(value)) {
                    sb = openTag(sb, type).append(valueForLeaf(value, type));
                    sb = closeTag(sb, type);
                } else {
                    sb = sb.append(describe(value));
                }
            } else {
                sb = openTag(sb, type).append("null");
                sb = closeTag(sb, type);
            }
        }
        return sb;
    }

    private static boolean needToBeDrawnAsIs(final Object value) {
        return classes.contains(value.getClass()) || !value.getClass().getCanonicalName()
                .contains("org.misha.beanutils.beans");
    }

    private static String calendarString(final GregorianCalendar c, final String name) {
        if (!name.contains("Tm")) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c.getTime().toString().replaceAll("00:00:00", EMPTY);
    }

    public static void main(String... args) {
        System.out.println(serialize(new Serializer().createActual()));
    }

    private Root createActual() {
        return Root.RootMaker.makeRoot();
    }
}

