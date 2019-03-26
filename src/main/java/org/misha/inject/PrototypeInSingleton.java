package org.misha.inject;

import javax.inject.Named;
import java.util.List;
import java.util.function.Function;

@Named
class PrototypeInSingleton {
    private final List<String> list;

    PrototypeInSingleton(List<String> list) {
        this.list = list;
    }

    void add(String s, Function<String, String> f) {
        list.add(f.apply(s));
    }

    @Override
    public String toString() {
        return String.format("Prototype{list=%s}", list);
    }
}
