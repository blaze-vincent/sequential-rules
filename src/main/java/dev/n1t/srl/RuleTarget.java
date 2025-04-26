package dev.n1t.srl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Base class for holding
 */
public abstract class RuleTarget {
    private final List<String> rulesFired = new ArrayList<>();

    public List<String> getRulesFired(){
        return rulesFired;
    }

    public void addRulesFired(String ...ruleNames){
        Collections.addAll(rulesFired, ruleNames);
    }
}
