package org.misha.proxy.proxyjdk;

/**
 * Author: mshevelin
 * Date: 2/2/15
 * Time: 4:26 PM
 */
public final class Main {
    public static void main(final String... args) {
        final MyObject t = MyObject.Factory.getInstance(1, "nnnn");
        t.printCount();
        t.printName(3);
        t.printName(1);
    }
}


