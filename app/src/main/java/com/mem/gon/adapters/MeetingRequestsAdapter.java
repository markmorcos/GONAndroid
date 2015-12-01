package com.mem.gon.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.activities.MeetingActivity;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.models.Meeting;
import com.mem.gon.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Mayar on 12/1/15.
 */
public class MeetingRequestsAdapter extends ArrayAdapter<Meeting> {

    public MeetingRequestsAdapter(Context context, int resource, Meeting[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Meeting meeting = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_meeting_requests, parent, false);
        }
        ImageView userPicture = (ImageView) convertView.findViewById(R.id.image_view_meeting_picture);
        userPicture.setImageResource(R.drawable.bamboo);
        userPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MeetingActivity.class);
                intent.putExtra("id", meeting.getId());
                getContext().startActivity(intent);
            }
        });
        TextView userName = (TextView) convertView.findViewById(R.id.text_view_place_name);
        userName.setText(meeting.getName());
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MeetingActivity.class);
                intent.putExtra("id", meeting.getId());
                getContext().startActivity(intent);
            }
        });

        Button goingButton = (Button) convertView.findViewById(R.id.button_meeting_accept);
        Button notGoingButton = (Button) convertView.findViewById(R.id.button_meeting_reject);
        return convertView;
    }
}
