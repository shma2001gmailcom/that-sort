package org.misha.combine;

import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * author: misha
 * date: 2/11/18
 * time: 11:51 AM
 */
public abstract class Combiner<Key, Element> implements Iterable<Element> {
    private final List<Element> collection;
    private final List<Element> combined = new ArrayList<Element>();
    private boolean hasBeenCombined = false;

    public Combiner(final List<Element> collection) {
        this.collection = collection;
    }

    protected abstract void onExistentKey(Key key, Element element, Map<Key, Element> map);

    protected abstract void onMissedKey(Key key, Element element, Map<Key, Element> map);

    protected abstract Key keyForElement(Element element);

    protected void combine() {
        checkArgument(!hasBeenCombined);
        final Map<Key, Element> map = new HashMap<Key, Element>();
        for (final Element element : collection) {
            final Key key = keyForElement(element);
            if (map.containsKey(key)) {
                onExistentKey(key, element, map);
            } else {
                onMissedKey(key, element, map);
            }
        }
        combined.addAll(map.values());
        hasBeenCombined = true;
    }

    @Override
    public Iterator<Element> iterator() {
        checkArgument(hasBeenCombined);
        return combined.iterator();
    }
}