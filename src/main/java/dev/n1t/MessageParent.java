package dev.n1t;

import dev.n1t.validator.RuleTarget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MessageParent extends RuleTarget {
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    public void addMessage(Message message){
        synchronized (messages) {
            messages.add(message);
        }
    }

    public List<Message> getMessages(){
        return messages;
    }
}
