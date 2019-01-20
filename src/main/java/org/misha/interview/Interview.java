package org.misha.interview;

import org.apache.log4j.Logger;

/**
 * author: misha
 * date: 6/24/15 8:53 PM.
 */
public class Interview {
    private static final Logger log = Logger.getLogger(Interview.class);

    public static void main(String... args) {
        base p = new Interview.derived();
        log.info(p.method1() + "\n==========================");
        String a = "a";
        String b = "b";
        a = a.concat(b);
        log.info(a + "c" + "\n=============================");
        B bb = new B();
    }

    static class base {
        public String method1() {
            return "base";
        }
    }

    private static class derived extends base {
        @Override
        public String method1() {
            return "derived";
        }
    }

    static class A {
        private String string = "Green";

        public A() {
            try {
                log.info("string: \"" + getString() + "\", length: " + getString().length());
            } catch (NullPointerException e) {
            }
        }

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }

    static class B extends A {
        private String string = "Red";

        @Override
        public String getString() {
            return string;
        }

        @Override
        public void setString(String string) {
            this.string = string;
        }
    }
}
