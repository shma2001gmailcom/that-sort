package org.misha.beanutils;

import org.apache.log4j.Logger;
import org.misha.beanutils.beans.*;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.misha.beanutils.BeansManipulator.RootMaker.makeRoot;

/**
 * author: misha
 * date: 4/13/16
 * time: 8:50 PM
 */
public class BeansManipulator {
    private static final Logger log = Logger.getLogger(BeansManipulator.class);
    public static void main(String... args) {
        Root root = makeRoot();
        log.info(MessageFormat.format("\n\n{0}", new Describer("org.misha.beanutils.beans").describe(root, 0)));
    }

    static class RootMaker {

        public static Root makeRoot() {
            Root root = new Root();
            Node0 node0 = new Node0();
            Node00 node00 = new Node00();
            node00.setInteger00(0);
            node0.setNode00(node00);
            Node01 node01 = new Node01();
            node01.setString01("string01");
            node0.setNode01(node01);
            root.setNode0(node0);
            Node1 node1 = new Node1();
            Node10 node10 = new Node10();
            Node100 node100 = new Node100();
            Node100 anotherNode100 = new Node100();
            node100.setS("s0");
            anotherNode100.setS("s1");
            node10.add(node100);
            node10.add(anotherNode100);
            Node11 node11 = new Node11();
            node1.add(node11);
            node1.add(node11);
            node1.setNode10(node10);
            root.setNode1(node1);
            root.setDate(new Date());
            return root;
        }
    }

    static class Describer {
        private final String beansPackage;

        Describer(String s) {
            beansPackage = s;
        }

        String describe(Object bean, int depth) {
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
                    sb = sb.append(describe(value, depth));
                } else if (value instanceof Collection) {
                    sb = sb.append(tab(depth)).append(value.getClass().getSimpleName()).append("{\n");
                    Collection collection = (Collection) value;
                    if (collection.isEmpty()) {
                        sb = sb.append(tab(depth)).append("[]\n");
                    } else {
                        for (Iterator it = collection.iterator(); it.hasNext(); ) {
                            Object element = it.next();
                            if (it.hasNext()) {
                                sb = sb.append(describe(element, depth + 1));
                            } else {
                                sb = sb.append(describe(element, depth + 1)).append("\n");
                            }
                        }
                        sb = sb.append(tab(depth)).append("}\n");
                    }
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

        private static String tab(int length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 2 * length; ++i) {
                sb = sb.append("-");
            }
            return sb.toString();
        }
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
