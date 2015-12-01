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
import com.mem.gon.activities.MeetingActivity;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.adapters.MeetingsAdapter;
import com.mem.gon.models.Meeting;
import com.mem.gon.models.User;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class MeetingsFragment extends Fragment {

    public MeetingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_meetings, container, false);
        ListView friendsList = (ListView) rootView.findViewById(R.id.list_view_meetings);
        Button meetingRquestsButton = (Button) rootView.findViewById(R.id.button_meeting_requests);
        meetingRquestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new MeetingRequestsFragment(), "Meeting Requests");
            }
        });
        final Meeting[] MeetingsArray = new Meeting[] {
                new Meeting("Starbucks", new User("ebram@gmail.com", "Ebram", "Mitry")),
                new Meeting("Kudam", new User("mayar@gmail.com", "Mayar", "Ali"))
        };
        friendsList.setAdapter(new MeetingsAdapter(getActivity(), R.layout.adapter_meetings, MeetingsArray));
        friendsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Meeting.class);
                intent.putExtra("id", MeetingsArray[i].getId());
                startActivity(intent);

            }
        });

        return rootView;
    }
}
