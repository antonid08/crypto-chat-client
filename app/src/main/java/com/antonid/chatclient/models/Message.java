package com.antonid.chatclient.models;


public class Message {

    private User sender;
    private String text;

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
