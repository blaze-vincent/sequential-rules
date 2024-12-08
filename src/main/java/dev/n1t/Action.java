package dev.n1t;

@FunctionalInterface
public interface Action<T, U> {
    void perform(T dataSource, U target);
}
