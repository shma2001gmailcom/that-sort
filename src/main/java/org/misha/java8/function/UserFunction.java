package org.misha.java8.function;

import org.misha.java8.User;

import java.util.function.Function;

import static org.misha.java8.Gender.FEMALE;
import static org.misha.java8.Gender.MALE;

public class UserFunction {
    public static void main(String... args) {
        final User user = new User(5, "Pit", FEMALE);
        System.out.println(new UserFunction().process(user, u->u.modifyAge(110)));
        System.out.println(new UserFunction().process(user, u->u.modifyGender(MALE)));
        System.out.println(new UserFunction().process(user, u->u.modifyName("Paula")));
        System.out.println(new UserFunction().process(user, u->u.modifyName("Paul").modifyAge(11).modifyGender(MALE)));
    }

    private User process(final User user, final Function<User, User> modifier) {
        return modifier.apply(user);
    }
}
