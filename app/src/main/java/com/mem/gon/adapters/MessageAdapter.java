package com.mem.gon.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.models.Message;
import com.mem.gon.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mark on 27/11/15.
 */
public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context, int resource, Message[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Message message = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_message, parent, false);
        }
        CircleImageView userPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_message_picture);
        userPicture.setImageResource(R.drawable.picture);
        TextView userText1 = (TextView) convertView.findViewById(R.id.text_view_message_text1);
        userText1.setText(message.getText());
        TextView userText2 = (TextView) convertView.findViewById(R.id.text_view_message_text2);
        userText2.setText(message.getText());
        if (message.getUser().getName().equals("Mark Morcos")) {
            userPicture.setVisibility(View.GONE);
            userText1.setVisibility(View.GONE);
            userText2.setVisibility(View.VISIBLE);
        } else {
            userPicture.setVisibility(View.VISIBLE);
            userText1.setVisibility(View.VISIBLE);
            userText2.setVisibility(View.GONE);
        }
        return convertView;
    }
}
