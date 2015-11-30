package com.mem.gon.models;

import java.util.ArrayList;

/**
 * Created by Mayar on 11/29/15.
 */
public class Meeting extends Model {

    private double latitude, longitude;
    private String name;
    User user;
    ArrayList<User> users;


    public Meeting(String title, User user) {
        latitude = 0;
        longitude = 0;
        this.name = title;
        this.user = user;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }



}
