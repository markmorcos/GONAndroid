package com.mem.gon.models;

import java.util.ArrayList;

/**
 * Created by mark on 01/12/15.
 */
public class Post extends Model {
    User user;
    User otherUser;
    String text;
    double latitude, longitude;
    String image;

    ArrayList<Comment> comments;

    public Post() {
        user = new User();
        otherUser = new User();
        text = "";
        latitude = 0;
        longitude = 0;
        image = "";
        comments = new ArrayList<>();
    }

    public Post(User user, User otherUser, String text, double latitude, double longitude, String image, ArrayList<Comment> comments) {
        this.user = user;
        this.otherUser = otherUser;
        this.text = text;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.comments = comments;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
