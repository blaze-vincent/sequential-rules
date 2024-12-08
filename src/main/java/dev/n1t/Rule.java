package dev.n1t;

import java.util.List;

public interface Rule<T, U> {

    String getName();
    List<Condition<T>> getConditions();
    Action<T, U> getAction();

    default void fire(T input, U actionTarget){
        for(Condition<T> condition : getConditions())
            if(!condition.isMet(input))
                return;

        getAction().perform(input, actionTarget);
    };
}
