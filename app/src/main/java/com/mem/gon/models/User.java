package com.mem.gon.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mark on 27/11/15.
 */
public class User extends Model {
    private String email, firstName, lastName, picture;
    private double latitude, longitude;
    private String facebookUID;
    private ArrayList<User> friends;

    public User() {
        id = 0;
        email = "";
        firstName = "";
        lastName = "";
        picture = "";
        latitude = 0;
        longitude = 0;
        facebookUID = "";
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public String getFacebookUID() {
        return facebookUID;
    }

    public void setFacebookUID(String facebookUID) {
        this.facebookUID = facebookUID;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User friend){
        this.friends.add(friend);
    }

    public String getName() {
        return firstName + " " + lastName;
    }


}
