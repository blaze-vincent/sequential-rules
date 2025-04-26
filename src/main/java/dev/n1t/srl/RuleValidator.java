package dev.n1t.srl;

import java.util.List;

public abstract class RuleValidator<T, U extends RuleTarget>{
    public abstract List<Rule<T, U>> getRules();

    public void runAllRules(T input, U actionTarget){
        for(Rule<T, U> rule : getRules())
            rule.fire(input, actionTarget);
    }
}