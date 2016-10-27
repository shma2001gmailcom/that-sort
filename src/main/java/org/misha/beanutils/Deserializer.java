package org.misha.beanutils;

import org.misha.beanutils.beans.Type;
import org.misha.beanutils.tree.Node;
import org.misha.beanutils.tree.Tree;
import org.misha.beanutils.tree.impl.Searcher;
import org.misha.beanutils.xml.Describer;
import org.misha.beanutils.xml.Xml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * author: misha
 * date: 4/30/16
 * time: 10:15 AM
 */
public class Deserializer {
    private final Tree<Data> tree;
    private final String pakkkage;
    private static Map<String, Type> map = new HashMap<String, Type>() {
        private static final long serialVersionUID = -8685406720026834192L;

        {
        put("Integer", Type.INTEGER);
        put("String", Type.STRING);
        put("Date", Type.DATE);
    }};

    public Deserializer(String p) {
        tree = createTree();
        pakkkage = p;
    }

    private Object deserealize(Node<Data> node) throws Exception {
        Object object = null;
        final Data content = node.getContent();
        final String type = content.getType();
        if (classNames().contains(type)) {
            if (map.containsKey(type)) {
                object = createLeaf(content.getValue(), type);
            } else if ("List".equalsIgnoreCase(type)) {
                final List<Object> list = new ArrayList<Object>();
                for (Node<Data> child : node.getChildren()) {
                    list.add(deserealize(child));
                    object = list;
                }
            } else {
                final String className = pakkkage + "." + type;
                object = createBean(className);
                if (object != null) {
                    for (Field field : object.getClass().getDeclaredFields()) {
                        if (field != null) {
                            final Collection<Node<Data>> children = node.getChildren();
                            for (Node<Data> child : children) {
                                if (field.getType().toString().contains(child.getContent().getType())) {
                                    field.setAccessible(true);
                                    field.set(object, deserealize(child));
                                }
                            }
                        }
                    }
                }
            }
        }  else {
            object = createLeaf(node.getContent().getValue(), node.getContent().getType());
        }
        return object;
    }

    private Object createLeaf(String s, String typeName) throws ParseException {
        final Type type = Type.getByName(typeName);
        switch (type) {
            case INTEGER:
                return Integer.parseInt(s);
            case DATE:
                return new SimpleDateFormat("EEE MMM DD HH:mm:ss Z yyyy").parse(s);
            case STRING:
                return s;
        }
        return null;
    }

    private Tree<Data> createTree() {
        try {
            return new Xml().parse(readFileToString(new File("src/test/resources/table")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object unmarshall(String xml) throws Exception {
        return deserealize(new Xml().parse(xml).root());
    }

    private Object createBean(String type) {
        try {
            return getClass().getClassLoader().loadClass(type).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> classNames() {
        List<String> result = new ArrayList<String>();
        Searcher<Data> searcher = new Searcher<Data>(tree.root()) {

            @Override
            public boolean isSuitable(Node<Data> node) {
                return true;
            }
        };
        searcher.search();
        for (Node<Data> node : searcher) {
            result.add(node.getContent().getType());
        }
        return result;
    }

    public static void main(String... args) throws Exception {//todo ms: enums haven't been deserealised(
        System.out.println(new Describer("org.misha.beanutils.beans").describe(new Deserializer("org.misha.beanutils.beans")
                .unmarshall(readFileToString(new File("src/test/resources/table"))), 0));
    }
}
