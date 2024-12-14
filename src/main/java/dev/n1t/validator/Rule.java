package dev.n1t.validator;

import java.util.List;

/**
 *
 * @param <T> Type of data evaluated by conditions and read from to apply action
 * @param <U> Type of data action is to be performed on
 */
public abstract class Rule<T, U> {

    public abstract String getName();
    public abstract List<Condition<T>> getConditions();
    public abstract Action<T, U> getAction();

    void fire(T input, U actionTarget){
        for(Condition<T> condition : getConditions())
            if(!condition.isMet(input))
                return;

        getAction().perform(input, actionTarget);
    };
}
