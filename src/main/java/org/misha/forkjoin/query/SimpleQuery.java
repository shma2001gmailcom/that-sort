package org.misha.forkjoin.query;

import java.lang.reflect.Method;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;
import static org.springframework.util.ReflectionUtils.*;

public class SimpleQuery implements Query {
    private final String fieldName;
    private final Object pattern;
    private final QueryPredicate queryPredicate;

    SimpleQuery(final String fieldName, final QueryPredicate queryPredicate, final Object pattern) {
        this.fieldName = fieldName;
        this.pattern = pattern;
        this.queryPredicate = queryPredicate;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getPattern() {
        return pattern;
    }

    public QueryPredicate getQueryPredicate() {
        return queryPredicate;
    }

    @Override
    public Predicate<Object> evaluate() {
        return o -> {
            Method method = findMethod(o.getClass(), "get" + capitalize(fieldName));
            checkArgument(method != null);
            method.setAccessible(true);
            Object property = invokeMethod(method, o);
            if (property == null) return pattern == null;
            final boolean equals = property.equals(pattern);
            switch (queryPredicate) {
                case LIKE:
                    return property.toString().contains(pattern.toString());
                case EQUALS:
                    return property.getClass().isAssignableFrom(pattern.getClass()) && equals;
                case NOT_EQUALS:
                    return property.getClass().isAssignableFrom(pattern.getClass()) && equals;
                default:
                    throw new IllegalStateException();
            }
        };
    }
}
