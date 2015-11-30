package com.mem.gon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.mem.gon.R;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.adapters.CurrentFriendsAdapter;
import com.mem.gon.adapters.FriendRequestsAdapter;
import com.mem.gon.models.User;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class FriendRequestsFragment extends Fragment {

    public FriendRequestsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_friend_requests, container, false);
        Button currentFriendsButton = (Button) rootView.findViewById(R.id.button_current_friends);
        currentFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new CurrentFriendsFragment(), "Current Friends");
            }
        });
        ListView friendsList = (ListView) rootView.findViewById(R.id.list_view_friends);
        final User[] friendsArray = new User[] {
                new User("ebram@gmail.com", "Ebram", "Mitry"),
                new User("mayar@gmail.com", "Mayar", "Ali"),
        };
        friendsList.setAdapter(new FriendRequestsAdapter(getActivity(), R.layout.adapter_friend_requests, friendsArray));
        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("id", friendsArray[i].getId());
                startActivity(intent);
            }
        });
        return rootView;
    }
}
