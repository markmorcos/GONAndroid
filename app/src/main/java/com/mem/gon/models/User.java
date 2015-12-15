package com.mem.gon.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mark on 27/11/15.
 */
public class User extends Model {
    private long friendId;
    private String email, firstName, lastName, picture;
    private double latitude, longitude;
    private String facebookUID;
    private ArrayList<User> friends;

    public User() {
        super();
        friendId = 0;
        email = "";
        firstName = "";
        lastName = "";
        picture = "";
        latitude = 0;
        longitude = 0;
        facebookUID = "";
    }

    public User(String email, String firstName, String lastName) {
        super();
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

    public long getFriendId() {
        return friendId;
    }

    public void setFriendId(long friendId) {
        this.friendId = friendId;
    }
    public static User fromJSON(JSONObject current) throws JSONException {
        User user = new User(current.getString("email"), current.getString("first_name"), current.getString("last_name"));
        user.setId(current.optLong("id"));
        user.setPicture(current.optString("picture"));
        user.setLatitude(current.optDouble("latitude"));
        user.setLongitude(current.optDouble("longitude"));
        user.setFacebookUID(current.optString("facebook_uid"));
        return user;
    }
}
