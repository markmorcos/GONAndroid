package com.mem.gon.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mark on 23/11/15.
 */
public class User extends Model {
    private String firstName, lastName, picture;
    private double latitude, longitude;
    private String facebookUID;

    public User() {
        super();
    }

    public User(long id) {

    }

    public static User fromJson(Context context, String url) {
        final User user = new User();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    user.setId(json.getLong("id"));
                    user.setFirstName(json.getString("first_name"));
                    user.setLastName(json.getString("last_name"));
                    user.setPicture(json.getString("picture"));
                    user.setLatitude(json.getDouble("latitude"));
                    user.setLongitude(json.getDouble("longitude"));
                    user.setFacebookUID(json.optString("facebook_uid"));
                    user.setCreatedAt(json.getString("created_at"));
                    user.setUpdatedAt(json.getString("updated_at"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(request);
        return user;
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
}
