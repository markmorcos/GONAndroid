package com.mem.gon.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.activities.MeetingActivity;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.adapters.FriendRequestsAdapter;
import com.mem.gon.adapters.MeetingRequestsAdapter;
import com.mem.gon.models.Meeting;
import com.mem.gon.models.User;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeetingRequestsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeetingRequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeetingRequestsFragment extends Fragment {
    public MeetingRequestsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meeting_requests, container, false);
        Button upcomingMeetingButton = (Button) rootView.findViewById(R.id.button_upcoming_meetings);
        upcomingMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getActivity()).setFragment(new MeetingsFragment(), "Upcoming Meetings");
            }
        });
        ListView meetingList = (ListView) rootView.findViewById(R.id.list_view_meetings);
        final Meeting[] meetingArray = new Meeting[]{
                new Meeting("Starbucks", new User("ebram@gmail.com", "Ebram", "Mitry")),
                new Meeting("Kudam", new User("mayar@gmail.com", "Mayar", "Ali"))
        };
        meetingList.setAdapter(new MeetingRequestsAdapter(getActivity(), R.layout.adapter_meeting_requests, meetingArray));
        meetingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MeetingActivity.class);
                intent.putExtra("id", meetingArray[i].getId());
                startActivity(intent);
            }
        });
        return rootView;
    }
}