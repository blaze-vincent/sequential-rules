package dev.n1t.validator;

/**
 *
 * @param <T>
 *     Type of input data, should be the same type as that received by conditions
 * @param <U>
 *     Type of target data to be interacted with in the action
 */
@FunctionalInterface
public interface Action<T, U> {
    void perform(T dataSource, U target);
}
