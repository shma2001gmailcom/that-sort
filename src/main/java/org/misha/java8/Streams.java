package org.misha.java8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;



public class Streams {

    public static void main(String... args) {
        new Streams().tryList();
    }

    private void tryList() {
        Collection<String> list = new ArrayList<String>() {
            {
                add("1");
                add("2");
                add("3");
                add(null);

            }
        };
        list.forEach(x -> Optional.ofNullable(x).ifPresent(System.out::println));
    }
}

