package org.misha.singletons;

/**
 * author: misha
 * date: 1/21/17
 * time: 4:09 PM
 */
public class FinalValue<T> {
    private final T value;

    public FinalValue(final T value) {
        this.value = value;
    }

    public T value() {
        return value;
    }
}
