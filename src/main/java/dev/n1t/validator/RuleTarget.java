package dev.n1t.validator;

import java.util.ArrayList;
import java.util.List;

public abstract class RuleTarget {
    private List<String> rulesFired = new ArrayList<>();

    public List<String> getRulesFired(){
        return rulesFired;
    }

    public void addRulesFired(String ...ruleNames){
        for(String ruleName : ruleNames)
            rulesFired.add(ruleName);
    }
}
