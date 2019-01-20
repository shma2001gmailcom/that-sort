package org.misha.java8.streams;

import org.misha.java8.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.misha.java8.Gender.FEMALE;
import static org.misha.java8.Gender.MALE;
import static org.misha.java8.Gender.UNKNOWN;

/**
 * author: misha
 * date: 6/23/18
 * time: 11:27 PM
 */
public class UserFilters {
    public static void main(String... args) {
        List<User> users =
                Arrays.asList(new User(5, "John", MALE), new User(7, "Hannah", FEMALE), new User(95, "Zuv", MALE));
        users.stream().filter(u -> u.getGender() == FEMALE).map(u -> u.getName() + " is female")
             .collect(Collectors.toList()).forEach(System.out::println);
        users.stream().filter(u -> u.getGender() == MALE).flatMap(u -> Stream.of(u.getName() + " is male"))
             .collect(Collectors.toList()).forEach(System.out::println);
        System.out.println(users.stream().filter(u -> u.getGender() == UNKNOWN).findFirst().orElse(null));
        new HashMap<Integer, User>() {
            private static final long serialVersionUID = 5943959406804806096L;

            {
                put(0, new User(65, "Pit", MALE));
                put(1, new User(43, "Joan", FEMALE));
                put(2, new User(67, "Sheila", FEMALE));
                put(3, new User(21, "Pit", MALE));
            }
        }.entrySet().stream().filter(u -> {
            int id = u.getKey();
            User user = u.getValue();
            String name = user.getName();
            return id > 0 && name.contains("a") && user.getGender() == MALE;
        }).collect(Collectors.toList()).forEach(System.out::println);
    }
}
