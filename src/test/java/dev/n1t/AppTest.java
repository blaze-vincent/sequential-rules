package dev.n1t;

import dev.n1t.validator.ConcurrentRuleValidator;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testApp()
    {
        ValidationRequest validationRequest = new ValidationRequest();
        validationRequest.setForm(new Form());

        int ruleCount = 1_000_000;
        ExampleRule[] clonesOfRule = new ExampleRule[ruleCount];
        for(int i = 0; i< clonesOfRule.length; i++)
            clonesOfRule[i] = new ExampleRule();

        ConcurrentRuleValidator<ValidationRequest, MessageParent> formRuleset = new ConcurrentRuleValidator<>(
                clonesOfRule
        );

        long timeStart = System.currentTimeMillis();
        formRuleset.runAllRules(validationRequest, validationRequest.getForm());
        long timeFinish = System.currentTimeMillis();

        long timeTotal = (timeFinish - timeStart);
        System.out.println("Rules run: " + ruleCount);
        System.out.println("Time overall: " + timeTotal + "ms");
        System.out.println("Time per rule: " + ((double) timeTotal / (double) ruleCount) + "ms");
    }
}