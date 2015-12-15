package com.mem.gon.util;

import android.app.DownloadManager;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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
        String url = BASE_URL + "/registrations/check_email";
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
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
            params.put("email", email);
            params.put("password", password);
            params.put("first_name", firstName);
            params.put("last_name", lastName);
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
            params.put("email", email);
            params.put("password", password);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // TODO
    public static void getNewsFeed(long id, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" +  id + "/news_feed";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void postAPost(long id, long friendId, String text, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/posts";
        JSONObject params = new JSONObject();
        try {
            params.put("id", id);
            params.put("other_user_id", friendId);
            params.put("text", text);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void commentAComment(long id, long postId, String text, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/comments";
        JSONObject params = new JSONObject();
        try {
            params.put("id", id);
            params.put("post_id", postId);
            params.put("text", text);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void getProfile(long id, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + id + "/profile";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void changePassword(long id, String password, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/settings", id);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void changeName(long id, String firstName, String lastName, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + id + "/change_name";
        try {
            JSONObject params = new JSONObject();
            params.put("first_name", firstName);
            params.put("last_name", lastName);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void changePhoto(long id, String picture, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + String.format("/users/%d%n/profile", id);
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getCurrentFriends(long id, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + id + "/current_friends";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getFriendRequests(long id, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + id + "/friend_requests";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }

    public static void getNotFriends(long id, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + id + "/not_friends";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, listener, errorListener);
        getRequestQueue().add(request);
    }


    public static void addFriend(long id, long otherId,  Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        String url = BASE_URL + "/users/" + otherId + "/add_friend";
        JSONObject params = new JSONObject();
        try {
            params.put("user_id", id);
            params.put("other_user_id", otherId);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public static void removeFriend(long id, long friendId, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener){
        String url = BASE_URL + "/users/" + friendId + "/remove_friend";
        JSONObject params = new JSONObject();
        try {
            params.put("user_id", id);
            params.put("other_user_id", friendId);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public static void acceptRejectFriend(long friendId, boolean accept, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "/users/" + friendId + "/respond";
        JSONObject params = new JSONObject();
        try {
            params.put("accepted", accept);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, params, listener, errorListener);
            getRequestQueue().add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
