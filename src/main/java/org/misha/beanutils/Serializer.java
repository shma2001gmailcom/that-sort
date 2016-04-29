package org.misha.beanutils;

import org.apache.commons.lang3.StringUtils;
import org.misha.beanutils.beans.Root;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 * author: misha
 * date: 29.04.2016.
 */
public class Serializer {
    public static final String XML_HEADER = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?>";
    public static final String LESS_THAN = "<";
    public static final String GREATER_THAN = ">";
    public static final String LESS_THAN_SLASH = "</";
    private static final HashSet<Class> classes = new HashSet<Class>();

    private Serializer() {}

    static {
        classes.add(String.class);
        classes.add(BigDecimal.class);
        classes.add(Boolean.class);
        classes.add(GregorianCalendar.class);
    }

    public static String serialize(final Object bean) {
        return XML_HEADER + describe(bean);
    }

    private static String describe(final Object bean) {
        if(bean == null) {
            return "<>null</>";
        }
        if(bean instanceof String) {
            return (String) bean;
        }
        if(bean instanceof Enum) {
            return ((Enum)bean).name();
        }
        StringBuilder sb = new StringBuilder(LESS_THAN).append(bean.getClass().getSimpleName()).append(GREATER_THAN);
        sb = describeFields(bean, sb);
        sb = closeTag(sb, bean.getClass().getSimpleName());
        return sb.toString();
    }

    private static StringBuilder describeFields(final Object bean, StringBuilder sb) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(bean);
            } catch (IllegalAccessException e) {
                sb = sb.append("EMPTY");
            }
            final String name = StringUtils.capitalize(field.getName());
            if (value != null) {
                if (value instanceof Collection) {
                    final Object[] objects = ((Collection)value).toArray();
                    for (Object o : objects) {
                        sb = sb.append(describe(o));
                    }
                } else if(needToBeDrawnAsIs(value)) {
                    sb = openTag(sb, name).append(valueForLeaf(value, name));
                    sb = closeTag(sb, name);
                } else {
                    sb = sb.append(describe(value));
                }
            } else {
                sb = openTag(sb, name).append("null");
                sb = closeTag(sb, name);
            }
        }
        return sb;
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

    private static boolean needToBeDrawnAsIs(final Object value) {
        return classes.contains(value.getClass()) ||
                !value.getClass().getCanonicalName().contains("org.misha.beanutils.beans");
    }

    private static String calendarString(final GregorianCalendar c, final String name) {
        if(!name.contains("Tm")) {
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
        }
        return c.getTime().toString().replaceAll("00:00:00", StringUtils.EMPTY);
    }

    public static void main(String... args) throws JAXBException {
        System.out.println(serialize(new Serializer().createActual()));
        QName qName = new QName("info.source4code.jaxb.model", "ListResponse");
        Root vasya = new Serializer().createActual();
        JAXBElement<Root> root = new JAXBElement<Root>(qName, Root.class, vasya);

        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        // output pretty printed
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(root, System.out);
    }

    private Root createActual() {
        return Root.RootMaker.makeRoot();
    }
}

