package com.mem.gon.models;

import android.os.Parcel;

/**
 * Created by mark on 02/12/15.
 */
public class Comment extends Model {
    User user;
    Post post;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
