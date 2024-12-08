package dev.n1t;

public interface Condition<T> {
    boolean isMet(T input);
}
