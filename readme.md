# Simple Rule Library (SRL)

## What is it?

This is a library providing barebones utilities for evaluating rules in Java.

## Should I use it?

- Do you want to write business rules without learning an entire ecosystem including a new programming language?
- Does your project need to run lots of sequential rules with no re-evaluation?

If so, this library may be useful to your project.

## Why does it exist?

A project I am working on features a large number of business rules which serve only to add error messages to the input
data.  
We were using FICO Blaze Advisor to run these rules, and due to the high license fees, we started looking at
alternatives.   
We explored Drools, but realized that it was going to be a high level of effort only to retain many of the features
developers hate.  
FICO Blaze Advisor and Drools both offer business users a UI for editing rules, which require a somewhat high level of
effort to maintain, but didn't and never would see any use for a number of reasons. Both require our Java developers to
spend a lot of time building specialized knowledge, including learning another programming language when they already
know Java. Both introduce a substantial overhead to running what are essentially if/else statements.  
### How is this different?
This solution makes no far-fetched claims of enabling business users to change program logic. It takes advantage of the
already-existing Java knowledge of our developers, allowing anyone on the team to modify rules. It provides a very slim
framework for writing the if/else statements that our rules are, with lots of opportunity for performance gains.

## How do I use it?

The following examples will consider the scenario where a DTO's fields must be validated beyond the schema.

Define your base input data, extending <code>dev.n1t.srl.RuleTarget</code>.

```java
// Message.java
package dev.n1t;

public record Message(String ruleName, String text, Object value) {
}
```

```java
// Form.java
package dev.n1t;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dev.n1t.srl.RuleTarget;

public class Form extends RuleTarget {
    // fields used in rule EX01
    private double value1;
    private double value2;

    private final List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public double getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }
}
```

Define the types of rules your project will be using, extending the <code>dev.n1t.srl.Rule</code> class.
Override the <code>getAction()</code> method with the logic to be applied when the conditions are met.

```java
// MessageRule.java
package dev.n1t;

import dev.n1t.srl.Action;
import dev.n1t.srl.Rule;

public abstract class MessageRule extends Rule<Form, MessageParent> {
    public abstract String getMessageText();

    public abstract Object getMessageValue(Form validationRequest);

    @Override
    public Action<Form, MessageParent> getAction() {
        return (form, msgParent) -> msgParent.addMessage(
                new Message(getName(), getMessageText(), getMessageValue(form)));
    }
    
    // note that if rules of a certain type share common conditions
    // you can override Rule.fire() to run those conditions before evaluating
    // rule-specific conditions
}
```

Implement the rule types you have defined.

```java
// ExampleRule.java
package dev.n1t;

import dev.n1t.validator.Condition;

import java.util.ArrayList;
import java.util.List;

public class ExampleRule extends MessageRule {
    @Override
    public String getName() {
        return "EX01";
    }

    //note that static conditions improve performance at runtime
    private static List<Condition<Form>> conditions = List.of(
            f -> f.getValue1() > 0,
            f -> f.getValue1() > f.getValue2()
    );

    @Override
    public List<Condition<Form>> getConditions() {
        return conditions;
    }

    @Override
    public String getMessageText() {
        return "If value1 has a value, it must be less than or equal to value2.";
    }

    @Override
    public Object getMessageValue(Form form) {
        return form.getValue1();
    }
}
```

Define a class extending <code>RuleValidator</code> for executing your rules.

```java
package dev.n1t;

import dev.n1t.srl.Rule;
import dev.n1t.srl.RuleValidator;

import java.util.List;

public class ExampleRuleset extends RuleValidator<Form, Form> {
    private static List<Rule<Form, Form>> rules = List.of(
            new ExampleRule()
    );

    @Override
    public List<Rule<Form, Form>> getRules() {
        return rules;
    }

    @Override
    public void runAllRules(Form input, Form actionTarget) {
        super.runAllRules(input, actionTarget);
    }
}
```

Finally, run rules by instantiating the class extending <code>RuleValidator</code> and calling <code>
runAllRules()</code>.