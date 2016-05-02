package org.misha.beanutils;

import java.util.Stack;
import java.util.regex.Pattern;

/**
 * author: misha
 * date: 4/30/16
 * time: 10:15 AM
 */
public class Deserializer {
    private Stack<Object> stack = new Stack<Object>();
    private Pattern startTag = Pattern.compile("<([a-zA-Z])>");
    private Pattern endTag = Pattern.compile("</([a-zA-Z])>");

    private String getTagTypeName(String group) {
        return group.replace("<", "").replace(">", "");
    }

    private Object createBean(String type) throws ClassNotFoundException, IllegalAccessException,
                                                  InstantiationException {
        return getClass().getClassLoader().loadClass(type).newInstance();
    }
}
