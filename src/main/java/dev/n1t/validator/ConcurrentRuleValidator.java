package dev.n1t.validator;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ConcurrentRuleValidator<T, U extends RuleTarget>{
    public ConcurrentRuleValidator(Rule<T, U> ...rules){
        this.rules = List.of(rules);
    }

    private final List<Rule<T, U>> rules;

    public void runAllRules(T input, U actionTarget){
        CountDownLatch latch = new CountDownLatch(rules.size());

        for(Rule<T, U> rule : rules){
            Thread.startVirtualThread(() -> {
                rule.fire(input, actionTarget);
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
