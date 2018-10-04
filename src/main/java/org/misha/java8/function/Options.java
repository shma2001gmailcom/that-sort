package org.misha.java8.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Options {
    private final Map<Predicate<Integer>, Function<Integer, Integer>> optionToAction;

    private Options() {
        optionToAction = new HashMap<>();
    }

    private static OptionsBuilder builder() {
        return new OptionsBuilder();
    }

    @SafeVarargs
    static Predicate<Integer> allOf(Predicate<Integer>... predicates) {
        Predicate<Integer> result = integer -> true;
        for (Predicate<Integer> p : predicates) {
            result = result.and(p);
        }
        return result;
    }

    @SafeVarargs
    static Predicate<Integer> anyOf(Predicate<Integer>... predicates) {
        Predicate<Integer> result = integer -> false;
        for (Predicate<Integer> p : predicates) {
            result = result.or(p);
        }
        return result;
    }

    public static void main(String... args) {
        Options.builder()
                .addRule(i -> i % 2 == 0, i -> i / 2)
                .addRule(i -> i % 3 == 0, i -> i / 3)
                .build()
                .processRules(i -> i < 1000);
    }

    private void addRule(final Predicate<Integer> condition, final Function<Integer, Integer> action) {
        optionToAction.put(condition, action);
    }

    private void processRules(Predicate<Integer> terminalCondition) {
        int i = 0;
        while (terminalCondition.test(i)) {
            for (Map.Entry<Predicate<Integer>, Function<Integer, Integer>> e : optionToAction.entrySet()) {
                if (e.getKey().test(i)) {
                    System.out.println(e.getValue().apply(i));
                }
            }
            ++i;
        }
    }

    private static class OptionsBuilder {
        private final Options result;

        private OptionsBuilder() {
            result = new Options();
        }

        OptionsBuilder addRule(final Predicate<Integer> condition, final Function<Integer, Integer> action) {
            result.addRule(condition, action);
            return this;
        }

        Options build() {
            return result;
        }
    }
}
