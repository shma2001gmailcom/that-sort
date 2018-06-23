package org.misha.immutables;

import org.immutables.value.Value;

/**
 * author: misha
 * date: 6/23/18
 * time: 12:52 PM
 */
@Value.Immutable
public abstract class Person {

    public static void main(String... args) {
        System.out.println(ImmutablePerson.builder().age(5).name("x").build());
    }

    abstract String getName();

    abstract Integer getAge();
}
