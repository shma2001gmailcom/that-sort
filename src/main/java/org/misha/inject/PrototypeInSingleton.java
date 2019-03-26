package org.misha.inject;

import javax.inject.Named;
import java.util.List;

@Named
public class PrototypeInSingleton {
    private final List<String> list;

    PrototypeInSingleton(List<String> list) {
        this.list = list;
    }

    public void add(String s) {
        list.add(s);
    }

    @Override
    public String toString() {
        return String.format("Prototype{list=%s}", list);
    }
}
