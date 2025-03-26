package dev.n1t.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class RuleTarget {
    private final List<String> rulesFired = Collections.synchronizedList(new ArrayList<>());

    public List<String> getRulesFired(){
        return rulesFired;
    }

    public void addRulesFired(String ...ruleNames){
        synchronized (rulesFired) {
            Collections.addAll(rulesFired, ruleNames);
        }
    }
}
