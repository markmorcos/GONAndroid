package com.mem.gon.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mark on 14/12/15.
 */
public class ApiClass {

    private static final String BASE_URL = "http://10.0.3.2:3000/api";
    public static Context context;
    private static RequestQueue requestQueue;

    private static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static void checkEmail(String email, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/check_email";
        JSONObject params = new JSONObject();
        try {
            params.put("user[email]", email);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void signUp(String email, String password, String firstName, String lastName, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users";
        JSONObject params = new JSONObject();
        try {
            params.put("user[email]", email);
            params.put("user[password]", password);
            params.put("user[first_name]", firstName);
            params.put("user[last_name]", lastName);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void login(String email, String password, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/sign_in";
        JSONObject params = new JSONObject();
        try {
            params.put("registration[email]", email);
            params.put("registration[password]", password);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getNewsFeed(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getProfile(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/profile", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void changePassword(long id, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/settings", id);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void changeName(long id, String firstName, String lastName, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/profile", id);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void changePhoto(long id, String picture, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/profile", id);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getCurrentFriends(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/current_friends", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getFriendRequests(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/friend_requests", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void acceptRejectFriend(long id, long friendId, boolean accept, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/friends/%d%n", id, friendId);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getMessages(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/messages", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void sendMessage(long id, long friendId, String text, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/messages/%d%n", id, friendId);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getUpcomingMeetings(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/user/%d%n/upcoming_meetings", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getMeetingRequests(long id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/user/%d%n/meeting_requests", id);
        StringRequest request = new StringRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void acceptRejectMeeting(long id, long meetingId, boolean accept, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/user/%d%n/meetings/%d%n", id, meetingId);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }
}
