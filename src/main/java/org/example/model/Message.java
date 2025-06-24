package org.example.model;

public class Message {
    private final String receiverEmail;
    private final String topic;
    private final String content;

    public Message(String receiverEmail, String topic, String content) {
        checkIfEmailIsValid(receiverEmail);
        this.receiverEmail = receiverEmail.trim();
        this.topic = topic.trim();
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return topic;
    }

    public String toCsv() {
        return String.format("%s,%s,%s", receiverEmail, topic, content);
    }


    private void checkIfEmailIsValid(String email) {
        if(!EmailValidator.validate(email)) {
            throw new IllegalArgumentException("Invalid email.");
        }
    }
}
