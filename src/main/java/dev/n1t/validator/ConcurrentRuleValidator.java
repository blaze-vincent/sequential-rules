package dev.n1t.validator;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ConcurrentRuleValidator<T, U>{
    public ConcurrentRuleValidator(Rule<T, U> ...rules){
        this.rules = List.of(rules);
    }

    private final List<Rule<T, U>> rules;

    public void runAllRules(T input, U actionTarget){
        CountDownLatch latch = new CountDownLatch(rules.size());

        for(Rule<T, U> rule : rules){
            Thread thread = new Thread(() -> {
                rule.fire(input, actionTarget);
                latch.countDown();
            });
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
//            System.out.printf("The rules have finished running. Rules triggered: %s%n", targetMessageParent.getMessages());
        }

    }
}
