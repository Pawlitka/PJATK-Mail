package org.example.model;

public class Message {
    private final String receiverEmail;
    private final String title;
    private final String content;

    public Message(String receiverEmail, String title, String content) {
        this.receiverEmail = receiverEmail;
        this.title = title;
        this.content = content;
    }


    public String getContent() {
        return content;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
