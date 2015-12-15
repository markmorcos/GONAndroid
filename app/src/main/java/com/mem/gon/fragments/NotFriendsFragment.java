package com.mem.gon.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.adapters.FriendRequestsAdapter;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by Mayar on 12/16/15.
 */
public class NotFriendsFragment extends Fragment {
    ProgressDialog dialog;
    public NotFriendsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_current_friends, container, false);
        Button friendRquestsButton = (Button) rootView.findViewById(R.id.button_friend_requests);
        Button currentFriendsButton = (Button) rootView.findViewById(R.id.button_current_friends);
        friendRquestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new FriendRequestsFragment(), "Friend Requests");
            }
        });

        currentFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new CurrentFriendsFragment(), "Current Friends");
            }
        });

        final ListView friendsList = (ListView) rootView.findViewById(R.id.list_view_not_friends);
        //TODO: get not friends list

        showLoadingDialog();
        final User[] NotFriendsArray;
        ApiClass.getNotFriends(Session.getUser().getId(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    final User[] notFriendsArray = new User[response.length()];
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject current = response.getJSONObject(i).getJSONObject("user");
                        if (current.getLong("id") == Session.getUser().getId()) {
                            current = response.getJSONObject(i).getJSONObject("other_user");
                        }
                        User user = new User(current.getString("email"), current.getString("first_name"), current.getString("last_name"));
                        user.setId(current.getLong("id"));
                        user.setFriendId(response.getJSONObject(i).getLong("id"));
                        user.setPicture(current.getString("picture"));
                        user.setLatitude(current.optDouble("latitude"));
                        user.setLongitude(current.optDouble("longitude"));
                        user.setFacebookUID(current.getString("facebook_uid"));
                        notFriendsArray[i] = user;
                    }
                    friendsList.setAdapter(new FriendRequestsAdapter(getActivity(), R.layout.adapter_not_friends, notFriendsArray));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hideLoadingDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideLoadingDialog();
            }
        });
        return rootView;
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }
}


