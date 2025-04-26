package dev.n1t.srl;

/**
 * Action to be executed after all conditions have been met in a rule.
 * @param <T>
 *     Type of input data, should be the same type as that received by conditions. It can be useful to access data from
 *     the input when performing the action, e.g. adding an erroneous field value to an error message
 * @param <U>
 *     Type of target data to be interacted with in the action
 */
@FunctionalInterface
public interface Action<T, U> {
    void perform(T dataSource, U target);
}