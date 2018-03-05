package org.misha.heterogenius;

/**
 * author: misha
 * date: 3/5/18
 */
public class Main {
    
    public static void main(String... args) {
        HeterogeneousContainer container = new HeterogeneousContainer();
        container.put(String.class, "String");
        container.put(Long.class, 0L);
        container.put(HeterogeneousContainer.class, new HeterogeneousContainer());
        for (Class<?> c : container) {
            System.out.println(container.get(c));
        }
    }
}
