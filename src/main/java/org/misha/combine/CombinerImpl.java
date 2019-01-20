package org.misha.combine;

import java.util.List;
import java.util.Map;

/**
 * author: misha
 * date: 2/11/18
 * time: 12:11 PM
 */
public class CombinerImpl extends Combiner<String, Integer> {
    CombinerImpl(List<Integer> collection) {
        super(collection);
    }

    @Override
    protected void onExistentKey(final String key, final Integer integer, final Map<String, Integer> map) {
        map.put(key, map.get(key) + integer);
    }

    @Override
    protected void onMissedKey(final String key, final Integer integer, final Map<String, Integer> map) {
        map.put(key, integer);
    }

    @Override
    protected String keyForElement(final Integer integer) {
        return String.valueOf(integer);
    }
}
