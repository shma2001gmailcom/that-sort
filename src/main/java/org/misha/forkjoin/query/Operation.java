package org.misha.forkjoin.query;

import java.util.function.Predicate;

public enum Operation {
    AND {
        @Override
        <T> Predicate<T> compute(final Predicate<T> left, final Predicate<T> right) {
            return left.and(right);
        }
    },
    OR {
        @Override
        <T> Predicate<T> compute(final Predicate<T> left, final Predicate<T> right) {
            return left.or(right);
        }
    },
    NOT {
        @Override
        <T> Predicate<T> compute(final Predicate<T> left, final Predicate<T> right) {
            return left.negate();
        }
    };

    abstract <T> Predicate<T> compute(Predicate<T> left, Predicate<T> right);
}
