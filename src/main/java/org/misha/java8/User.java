package org.misha.java8;

/**
 * author: misha
 * date: 6/23/18
 * time: 10:34 PM
 */
public class User {
    private final int age;
    private final String name;
    private final Gender gender;

    public User(int age, String name, Gender gender) {
        this.age = age;
        this.name = name;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }
}
