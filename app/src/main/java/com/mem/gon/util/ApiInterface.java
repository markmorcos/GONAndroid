package com.mem.gon.util;

import android.telecom.Call;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mem.gon.models.Meeting;
import com.mem.gon.models.Message;
import com.mem.gon.models.User;

import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Mayar on 12/13/15.
 */
public interface ApiInterface {

    @POST("/sessions")
    @FormUrlEncoded
    void login(@Field("session[email]") String email,
               @Field("session[password]") String password, Callback<User> callback);

    @GET("/user/{id}")
    void getNewsFeed(@Path("id") int id, Callback<User> callback);

    @GET("/user/{id}/profile")
    void getProfile(@Path("id") int id, Callback<User> callback);

    @PUT("/user/{id}/settings")
    @FormUrlEncoded
    void changePassword(@Path("id") int id, @Field("user[password]") String password, Callback<User> callback);

    @PUT("/user/{id}/profile")
    void changeName(@Path("id") int id, @Field("user[firstName]") String firstName, @Field("user[lastName]") String lastName,
            Callback<User> callback);

    @PUT("/user/{id}")
    void changePhoto(@Path("id") int id, @Field("user[picture") String picture, Callback<User> callback);

    @GET("/user/{id}/current_friends")
    void getCurrentFriends(@Path("id") int id, Callback<List<User>> callback);

    @GET("/user/{id}/friend_requests")
    void getFriendRequests(@Path("id") int id, Callback<List<User>> callback);

    @PUT("/user/{id}/friend/{friend_id}")
    void acceptRejectFriend(@Path("id") int id, @Path("friend_id") int friendID,
                            @Field("friend[accept]") boolean accept, Callback<User> callback);

    @GET("/user/{id}/messages")
    void getMessages(@Path("id") int id, Callback<List<Message>> callback);

    @PUT("/user/{id}/message/{message_id}")
    void sendMessage(@Path("id") int id, @Path("message_id") int messageID,
                     @Field("message[text]") String text, Callback<Message> callback);

    @GET("/user/{id}/upcoming_meetings")
    void getUpcomingMeetings(@Path("id") int id, Callback<List<Meeting>> callback);

    @GET("/user/{id}/meeting_requests")
    void getMeetingRequests(@Path("id") int id, Callback<List<Meeting>> callback);

    @PUT("/user/{id}/meeting/{meeting_id}")
    void acceptRejectMeeting(@Path("id") int id, @Path("meeting_id") int meetingID,
                             @Field("meeting[accept]") boolean accept, Callback<Meeting> callback);




}

