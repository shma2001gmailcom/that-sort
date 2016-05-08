package org.misha.beanutils;

import org.apache.log4j.Logger;
import org.misha.beanutils.beans.Root;
import org.misha.beanutils.beans.RootMaker;
import org.misha.beanutils.xml.Describer;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 * author: misha
 * date: 4/13/16
 * time: 8:50 PM
 */
public class BeanManipulator {
    private static final Logger log = Logger.getLogger(BeanManipulator.class);

    public static void main(String... args) throws IllegalAccessException, NoSuchMethodException,
                                                   InvocationTargetException {
        Root root = RootMaker.makeRoot();
        log.info(MessageFormat.format("\n\n{0}", new Describer("org.misha.beanutils.beans").describe(root, 0)));
    }
}
