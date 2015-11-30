package com.mem.gon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mark on 27/11/15.
 */
public class MessagesAdapter extends ArrayAdapter<User> {

    public MessagesAdapter(Context context, int resource, User[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_messages, parent, false);
        }
        CircleImageView userPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_messages_picture);
        userPicture.setImageResource(R.drawable.picture);
        TextView userName = (TextView) convertView.findViewById(R.id.text_view_messages_name);
        userName.setText(user.getName());
        return convertView;
    }
}
