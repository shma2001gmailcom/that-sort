package org.misha.forkjoin;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

class ForkJoinSearch<T> {
    private final List<T> results;

    ForkJoinSearch() {
        results = new CopyOnWriteArrayList<>();
    }

    List<T> search(final List<T> input, final Predicate<T> predicate) {
        ForkJoinPool.commonPool().invoke(new ForkJoinSearchTask<>(input, predicate, results));
        return ImmutableList.copyOf(results);
    }
}
