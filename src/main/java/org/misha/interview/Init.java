package org.misha.interview;

public class Init {
    private int i;

    private Init(int i) {
        this.i = i;
    }

    public static void main(String... args) {
        Init a = new Init(5);
        a.doIt();
    }

    private void doIt() {
        i = 2;
    }
}

