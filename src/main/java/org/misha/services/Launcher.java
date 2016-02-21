package org.misha.services;

/**
 * Author: mshevelin
 * Date: 10/8/14
 * Time: 3:40 PM
 */

public final class Launcher {
    private Launcher() {
    }

    public static void main(final String[] args) {
        org.misha.services.service.Linker.getInstance().link();
    }
}
