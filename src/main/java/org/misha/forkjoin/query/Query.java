package org.misha.forkjoin.query;

import java.util.function.Predicate;

public interface Query {
    Predicate<Object> evaluate();
}
