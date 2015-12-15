package com.mem.gon.util;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Mayar on 12/13/15.
 */
public interface ApiInterface {

//    @POST("/registrations/create")
    void checkEmail(String email, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @POST("/registrations/create")
    void signUp(String email, String password, String passwordConfirmation, String firstName, String lastName, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @POST("/sessions/create")
    void login(String email, String password, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}")
    void getNewsFeed(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/profile")
    void getProfile(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}/settings")
    void changePassword(long id, String password, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}/name")
    void changeName(long id, String firstName, String lastName, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}")
    void changePhoto(long id, String picture, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/current_friends")
    void getCurrentFriends(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/friend_requests")
    void getFriendRequests(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}/friend/{friend_id}")
    void acceptRejectFriend(long id, long friendId, boolean accept, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/messages")
    void getMessages(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}/message/{message_id}")
    void sendMessage(long id, long messageId, String text, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/upcoming_meetings")
    void getUpcomingMeetings(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @GET("/user/{id}/meeting_requests")
    void getMeetingRequests(long id, Response.Listener<String> listener, Response.ErrorListener errorListener);

//    @PUT("/user/{id}/meeting/{meeting_id}")
    void acceptRejectMeeting(long id, long meetingId, boolean accept, Response.Listener<String> listener, Response.ErrorListener errorListener);
}

