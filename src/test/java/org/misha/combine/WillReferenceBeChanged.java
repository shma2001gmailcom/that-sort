package org.misha.combine;

import org.apache.log4j.Logger;
import org.junit.Test;

public class WillReferenceBeChanged {
    private static final Logger log = Logger.getLogger(WillReferenceBeChanged.class);

    @Test
    public void test() {
        int i = 0;
        X x = new X(i);
        Y y = new Y(x);
        Y z = new Y(x);
        log.debug("\ni=" + i + "\nx=" + x + "\ny=" + y + "\nz=" + z);
        ++i;
        log.debug("\ni=" + i + "\nx=" + x + "\ny=" + y + "\nz=" + z);
        x.inc();
        log.debug("\ni=" + i + "\nx=" + x + "\ny=" + y + "\nz=" + z);
        x.dec();
        log.debug("\ni=" + i + "\nx=" + x + "\ny=" + y + "\nz=" + z);
        y.inc();
        log.debug("\ny.inc() affects z: \ni=" + i + "\nx=" + x + "\ny=" + y + "\nz=" + z);
    }

    class X {
        private int i;

        X(int i) {
            this.i = i;
        }

        void inc() {
            ++i;
        }

        void dec() {
            --i;
        }

        @Override
        public String toString() {
            return "{i=" + i + "}";
        }
    }

    class Y {
        private static final String type = "Y";
        private X x;

        Y(X x) {
            this.x = x;
        }

        void inc() {
            x.inc();
        }

        @Override
        public String toString() {
            return "{x=" + x.toString() + "}";
        }
    }
}
