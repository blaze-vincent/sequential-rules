package dev.n1t;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MessageRuleValidator<T, U>{
    public MessageRuleValidator(MessageRule ...rules){
        this.rules = List.of(rules);
    }

    private final List<MessageRule> rules;

    public void runAllRules(ValidationRequest validationRequest, MessageParent targetMessageParent){
        CountDownLatch latch = new CountDownLatch(rules.size());

        for(MessageRule rule : rules){
            Thread thread = new Thread(() -> {
                rule.fire(validationRequest, targetMessageParent);
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
