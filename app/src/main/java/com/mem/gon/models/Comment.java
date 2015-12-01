package com.mem.gon.models;

/**
 * Created by mark on 02/12/15.
 */
public class Comment extends Model {
    User user;
    String text;

    public Comment() {
        user = new User();
        text = "";
    }

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
