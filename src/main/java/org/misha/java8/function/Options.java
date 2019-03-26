package org.misha.java8.function;

import java.util.Arrays;
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
        return Arrays.stream(predicates).reduce(Predicate::and).orElse(i -> true);
    }

    @SafeVarargs
    static Predicate<Integer> anyOf(Predicate<Integer>... predicates) {
        return Arrays.stream(predicates).reduce(Predicate::and).orElse(i -> false);
    }

    public static void main(String... args) {
        Options.builder()
               .addRule(i -> i % 2 == 0, i -> i / 2)
               .addRule(i -> i % 3 == 0, i -> i / 3)
               .build()
               .processRules(i -> i < 1000);
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
            doAddRule(condition, action);
            return this;
        }

        private void doAddRule(final Predicate<Integer> condition, final Function<Integer, Integer> action) {
            result.optionToAction.put(condition, action);
        }

        Options build() {
            return result;
        }
    }
}
