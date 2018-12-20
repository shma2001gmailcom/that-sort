package org.misha.combine;

import javax.annotation.Nonnull;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/11/18
 * time: 11:51 AM
 */
public abstract class Combiner<K, E> implements Iterable<E> {
    private final List<E> collection;
    private final List<E> combined;
    private boolean hasBeenCombined = false;

    Combiner(final List<E> collection) {
        combined = new ArrayList<>();
        this.collection = collection;
    }

    protected abstract void onExistentKey(K key, E element, Map<K, E> map);

    protected abstract void onMissedKey(K key, E element, Map<K, E> map);

    protected abstract K keyForElement(E element);

    void combine() {
        checkArgument(!hasBeenCombined, "Already has been combined once.");
        final Map<K, E> map = new HashMap<>();
        for (final E element : collection) {
            final K key = keyForElement(element);
            if (map.containsKey(key)) {
                onExistentKey(key, element, map);
            } else {
                onMissedKey(key, element, map);
            }
        }
        combined.addAll(map.values());
        hasBeenCombined = true;
    }

    @Nonnull
    @Override
    public Iterator<E> iterator() {
        checkArgument(hasBeenCombined);
        return combined.iterator();
    }
}