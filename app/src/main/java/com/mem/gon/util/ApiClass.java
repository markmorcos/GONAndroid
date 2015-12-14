package com.mem.gon.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by mark on 14/12/15.
 */
public class ApiClass {

    private static final String BASE_URL = "http://localhost:3000";
    private static Context context;
    private static RequestQueue requestQueue;

    public ApiClass(Context context) {
        this.context = context;
    }

    private static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static void checkEmail(String email, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/registrations/email";
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void signUp(String email, String password, String firstName, String lastName, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/registrations/create";
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void login(String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/sessions/create";
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
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
