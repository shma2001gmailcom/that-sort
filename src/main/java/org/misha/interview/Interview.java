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
        try {
            B bb = new B();
        } catch (Throwable x) {
            log.info("Main exception: " + x + " A.string has not been initialized yet.");
        }
    }

    static class base {
        public String method1() {
            return "base";
        }
    }

    static class derived extends base {
        public String method1() {
            return "derived";
        }
    }

    static class A {
        private String string = "Green";

        public A() {
            log.info("string: \"" + getString() +
                             "\", length: " + getString().length()
            );
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

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }
    }
}
