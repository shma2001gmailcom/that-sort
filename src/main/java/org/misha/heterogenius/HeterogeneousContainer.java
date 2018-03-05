package org.misha.heterogenius;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author: misha
 * date: 3/5/18
 */
public class HeterogeneousContainer implements Iterable<Class<?>> {
    private final Map<Class<?>, Object> content = new HashMap<Class<?>, Object>();
    
    <T> void put(Class<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");
        content.put(type, type.cast(instance));
    }
    
    <T> T get(Class<T> type) {
        return type.cast(content.get(type));
    }
    
    @Override
    public Iterator<Class<?>> iterator() {
        return content.keySet().iterator();
    }
}
