package dev.n1t;

import dev.n1t.validator.Condition;

import java.util.ArrayList;
import java.util.List;

public class ExampleRule extends MessageRule {
    @Override
    public String getName() {
        return "EX01";
    }

    @Override
    public List<Condition<ValidationRequest>> getConditions() {
        return conditions;
    }

    private List<Condition<ValidationRequest>> conditions = new ArrayList<>();

    @Override
    public String getMessageText() {
        return "This is just an example rule";
    }

    @Override
    public Object getMessageValue(ValidationRequest request) {
        return request.getForm().getTotalValue();
    }
}
