package org.misha.threads.waitnotify.ticktack.multivalue;

import static com.google.common.base.Preconditions.checkArgument;

class Modulo {
    private final int m;

    Modulo(final int modulo) {
        checkArgument(modulo > 0);
        this.m = modulo;
    }

    int modulo(int i) {
        if (i >= 0) {
            return i % m;
        }
        int result = i;
        while (result < 0) {
            result += m;
        }
        return result;
    }
}
