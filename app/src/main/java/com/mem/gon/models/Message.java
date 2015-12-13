package com.mem.gon.models;

/**
 * Created by mark on 27/11/15.
 */
public class Message extends Model {
    User user, otherUser;
    String text;
    boolean read, received;

    public Message() {
        user = new User();
        otherUser = new User();
        text = "";
    }

    public Message(User user, User otherUser, String text) {
        this.user = user;
        this.otherUser = otherUser;
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isReceived() {
        return received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getOtherUser() {
        return otherUser;
    }

    public void setOtherUser(User otherUser) {
        this.otherUser = otherUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
