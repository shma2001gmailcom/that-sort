package org.misha.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.function.Predicate;

class ForkJoinSearchTask<T> extends RecursiveAction {
    private final transient List<T> toSearch;
    private final transient Predicate<T> criteria;
    private final transient List<T> results;

    ForkJoinSearchTask(final List<T> toSearch, final Predicate<T> criteria, final List<T> results) {
        this.toSearch = toSearch;
        this.criteria = criteria;
        this.results = results;
    }

    @Override
    public void compute() {
        if (toSearch.isEmpty()) return;
        if (toSearch.size() == 1) {
            final T value = toSearch.get(0);
            if (value != null && criteria.test(value)) {
                results.add(value);
            }
        } else {
            ForkJoinTask.invokeAll(splitTask());
        }
    }

    private List<ForkJoinSearchTask<T>> splitTask() {
        final List<ForkJoinSearchTask<T>> subTasks = new ArrayList<>(2);
        final int size = toSearch.size();
        subTasks.add(new ForkJoinSearchTask<>(toSearch.subList(0, size / 2), criteria, results));
        subTasks.add(new ForkJoinSearchTask<>(toSearch.subList(size / 2, size), criteria, results));
        return subTasks;
    }
}