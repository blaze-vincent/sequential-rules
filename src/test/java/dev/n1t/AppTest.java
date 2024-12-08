package dev.n1t;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
        ValidationRequest validationRequest = new ValidationRequest();
        validationRequest.setForm(new Form());

        ExampleRule[] clonesOfRule = new ExampleRule[10000];
        for(int i = 0; i< clonesOfRule.length; i++)
            clonesOfRule[i] = new ExampleRule();

        MessageRuleValidator<ValidationRequest, Form> formRuleset = new MessageRuleValidator<ValidationRequest, Form>(
                clonesOfRule
        );
        formRuleset.runAllRules(validationRequest, validationRequest.getForm());

    }
}
