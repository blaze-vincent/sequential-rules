package dev.n1t;

import dev.n1t.validator.Action;
import dev.n1t.validator.Rule;

public abstract class MessageRule extends Rule<ValidationRequest, MessageParent> {
    public abstract String getMessageText();

    public abstract Object getMessageValue(ValidationRequest validationRequest);

    @Override
    public Action<ValidationRequest, MessageParent> getAction() {
        return (validationRequest, msgParent) -> msgParent.addMessage(
                new Message(getName(), getMessageText(), getMessageValue(validationRequest)));
    }
}
