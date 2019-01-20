package org.misha.heterogenius;

import static org.junit.Assert.assertTrue;

/**
 * author: misha
 * date: 3/5/18
 */
public class Main {
    public static void main(String... args) throws IllegalAccessException, InstantiationException {
        HeterogeneousContainer container = new HeterogeneousContainer();
        container.put("string", String.class, "String");
        container.put("0", Long.class, 0L);
        container.put("HeterogeneousContainer", HeterogeneousContainer.class, new HeterogeneousContainer());
        container.put("HeterogeneousContainer1", HeterogeneousContainer.class, new HeterogeneousContainer());
        for (String s : container) {
            Class<?> c = container.getType(s);
            if (c.equals(HeterogeneousContainer.class)) {
                HeterogeneousContainer container1 = (HeterogeneousContainer) c.newInstance();
                container1.put("parent", HeterogeneousContainer.class, container);
            }
            assertTrue(container.size() == 4);
        }
    }
}
