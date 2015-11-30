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
import com.mem.gon.models.Meeting;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by Mayar on 11/29/15.
 */
public class MeetingsAdapter extends ArrayAdapter<Meeting> {

    public MeetingsAdapter(Context context, int resource, Meeting[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Meeting meeting = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_meetings, parent, false);
        }
        ImageView meetingPicture = ((ImageView) convertView.findViewById(R.id.circle_image_view_meeting_picture));
        meetingPicture.setImageResource(R.drawable.bamboo);
        meetingPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getContext()).startActivity(new Intent(getContext(), MeetingActivity.class));
            }
        });
        TextView meetingName = (TextView) convertView.findViewById(R.id.text_view_meeting_name);
        meetingName.setText(meeting.getName());
        meetingName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getContext()).startActivity(new Intent(getContext(), MeetingActivity.class));
            }
        });
        TextView host;
        host = (TextView) convertView.findViewById(R.id.host_name);
        host.setText(host.getText() + meeting.getUser().getName());
        Button buttonRemove = (Button) convertView.findViewById(R.id.button_meeting_remove);
        return convertView;
    }
}
