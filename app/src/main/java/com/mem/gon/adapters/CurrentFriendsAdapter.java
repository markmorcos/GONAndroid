package com.mem.gon.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.fragments.MessageFragment;
import com.mem.gon.models.User;

import de.hdodenhof.circleimageview.CircleImageView;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by mark on 27/11/15.
 */
public class CurrentFriendsAdapter extends ArrayAdapter<User> {

    public CurrentFriendsAdapter(Context context, int resource, User[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_current_friends, parent, false);
        }
        CircleImageView userPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_friend_picture);
        userPicture.setImageResource(R.drawable.picture);
        userPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("id", user.getId());
                getContext().startActivity(intent);
            }
        });
        TextView userName = (TextView) convertView.findViewById(R.id.text_view_friend_name);
        userName.setText(user.getName());
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("id", user.getId());
                getContext().startActivity(intent);
            }
        });
        Button meetButton = (Button) convertView.findViewById(R.id.button_friend_meet);
        Button messageButton = (Button) convertView.findViewById(R.id.button_friend_message);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MaterialNavigationDrawer) getContext()).setFragmentChild(new MessageFragment(), "You and " + user.getName());
            }
        });
        Button removeButton = (Button) convertView.findViewById(R.id.button_friend_remove);
        return convertView;
    }
}
