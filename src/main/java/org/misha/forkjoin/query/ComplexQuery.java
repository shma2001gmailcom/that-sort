package org.misha.forkjoin.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ComplexQuery implements Query {
    private final List<Query> queries;
    private final Operation operation;

    ComplexQuery(final Operation operation, final Query... queries) {
        this.queries = new ArrayList<>();
        this.queries.addAll(Arrays.asList(queries));
        this.operation = operation;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public Predicate<Object> evaluate() {
        return queries.stream().map(Query::evaluate).reduce(operation::compute).orElse(null);
    }
}
