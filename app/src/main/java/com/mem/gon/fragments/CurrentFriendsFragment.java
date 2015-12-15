package com.mem.gon.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.mem.gon.activities.MainActivity;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.adapters.CurrentFriendsAdapter;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class CurrentFriendsFragment extends Fragment {
    ProgressDialog dialog;
    public CurrentFriendsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_current_friends, container, false);
        Button friendRquestsButton = (Button) rootView.findViewById(R.id.button_friend_requests);
        Button notFriendButton = (Button) rootView.findViewById(R.id.button_not_friends);
        friendRquestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new FriendRequestsFragment(), "Friend Requests");
            }
        });

        notFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new NotFriendsFragment(), "Not Friends");
            }
        });
        final ListView friendsList = (ListView) rootView.findViewById(R.id.list_view_friends);
        showLoadingDialog();
        final User[] friendsArray;
        ApiClass.getCurrentFriends(Session.getUser().getId(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    final User[] friendsArray = new User[response.length()];
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject current = response.getJSONObject(i).getJSONObject("user");
                        if (current.getLong("id") == Session.getUser().getId()) {
                            current = response.getJSONObject(i).getJSONObject("other_user");
                        }
                        User user = new User(current.getString("email"), current.getString("first_name"), current.getString("last_name"));
                        user.setId(current.getLong("id"));
                        user.setPicture(current.getString("picture"));
                        user.setLatitude(current.optDouble("latitude"));
                        user.setLongitude(current.optDouble("longitude"));
                        user.setFacebookUID(current.getString("facebook_uid"));
                        friendsArray[i] = user;
                    }
                    friendsList.setAdapter(new CurrentFriendsAdapter(getActivity(), R.layout.adapter_current_friends, friendsArray));
                    friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity(), ProfileActivity.class);
                            intent.putExtra("id", friendsArray[i].getId());
                            startActivity(intent);
                        }
                    });
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
