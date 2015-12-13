package com.mem.gon.models;

/**
 * Created by Mayar on 12/13/15.
 */
public class MeetingRequest extends Model{
    User user, otherUser;
    boolean accept;
    Meeting meeting;

    public MeetingRequest(User user, User otherUser, Meeting meeting){
        this.user = user;
        this.otherUser = otherUser;
        this.meeting = meeting;
    }
    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }


}
