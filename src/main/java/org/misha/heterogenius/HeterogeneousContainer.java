package org.misha.heterogenius;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * author: misha
 * date: 3/5/18
 */
public class HeterogeneousContainer implements Iterable<String> {
    private final Map<String, ObjectWithType<?>> content = new HashMap<>();
    
    @Nonnull
    @Override
    public Iterator<String> iterator() {
        return content.keySet().iterator();
    }
    
    <T> void put(String key, Class<T> type, T instance) {
        content.put(key, new ObjectWithType<>(instance, type));
    }
    
     <T> T get(String key) {
        return (T) getType(key).cast(content.get(key).value);
    }
    
    <T> Class<T> getType(String s) {
        return (Class<T>) content.get(s).type;
    }
    
    public int size() {
        return content.size();
    }
    
    public class ObjectWithType<T> {
        private T value;
        private Class<T> type;
        
        private  ObjectWithType(T value, Class<T> type) {
            this.value = value;
            this.type = type;
        }
        
        public T get() {
            return type.cast(value);
        }
    }
}
