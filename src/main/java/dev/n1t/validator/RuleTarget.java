package dev.n1t.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RuleTarget {
    private List<String> rulesFired = Collections.synchronizedList(new ArrayList<>());

    public List<String> getRulesFired(){
        return rulesFired;
    }

    public void addRulesFired(String ...ruleNames){
        synchronized (rulesFired) {
            for(String ruleName : ruleNames)
                rulesFired.add(ruleName);
        }
    }
}
