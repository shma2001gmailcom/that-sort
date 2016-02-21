package org.misha.interview;

/**
 * author: misha
 * date: 6/24/15 8:53 PM.
 */
public class Interview {
    public static void main(String... args) {
        base p = new Interview.derived();
        System.out.println(p.method1() + "\n==========================");
        String a = "a";
        String b = "b";
        a = a.concat(b);
        System.out.println(a + "c" + "\n=============================");
        try {
            B bb = new B();
        } catch (Throwable x) {
            System.out.println("Main exception: " + x);
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
            System.out.println("string: \"" + getString() +
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
