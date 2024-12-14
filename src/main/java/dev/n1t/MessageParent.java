package dev.n1t;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class MessageParent {
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    public void addMessage(Message message){
        messages.add(message);
    }

    public List<Message> getMessages(){
        return messages;
    }
}
