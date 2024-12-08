package dev.n1t;

public abstract class MessageRule implements Rule<ValidationRequest, MessageParent> {
    public abstract String getMessageText();

    public abstract Object getMessageValue(ValidationRequest validationRequest);

    @Override
    public Action<ValidationRequest, MessageParent> getAction() {
        return (validationRequest, msgParent) -> msgParent.addMessage(
                new Message(getName(), getMessageText(), getMessageValue(validationRequest)));
    }
}
