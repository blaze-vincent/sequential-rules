package dev.n1t.srl;

import java.util.List;

/**
 * The base class from which all rules derive.
 * @param <T> Type of data evaluated by conditions and read from to apply action
 * @param <U> Type of data action is to be performed on
 */
public abstract class Rule<T, U extends RuleTarget> {

    public abstract String getName();
    public abstract List<Condition<T>> getConditions();
    public abstract Action<T, U> getAction();

    void fire(T input, U actionTarget){
        for(Condition<T> condition : getConditions())
            if(!condition.isMet(input))
                return;

        actionTarget.addRulesFired(getName());
        getAction().perform(input, actionTarget);
    }
}
