package com.mem.gon.models;

/**
 * Created by Mayar on 12/13/15.
 */
public class Friend extends Model {
    User user, otherUser;
    boolean accept;

    public Friend(User user, User otherUser, boolean accept){
        this.user = user;
        this.otherUser = otherUser;
        this.accept = accept;
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

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

}
