package com.mem.gon.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.fragments.NotFriendsFragment;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;
import org.json.JSONObject;
import de.hdodenhof.circleimageview.CircleImageView;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
/**
 * Created by Mayar on 12/16/15.
 */

public class NotFriendAdapter extends ArrayAdapter<User> {
    ProgressDialog dialog;
    public NotFriendAdapter(Context context, int resource, User[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_not_friends, parent, false);
        }
        CircleImageView userPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_friend_picture);
        userPicture.setImageResource(R.drawable.picture);
        TextView userName = (TextView) convertView.findViewById(R.id.text_view_friend_name);
        userName.setText(user.getName());
        Button addFriend = (Button) convertView.findViewById(R.id.button_add_friend);

        //TODO: perform add function
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                ApiClass.addFriend(Session.getUser().getId(), user.getFriendId(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ((MaterialNavigationDrawer) getContext()).setFragmentChild(new NotFriendsFragment(), "Not Friends");
                        hideLoadingDialog();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                        hideLoadingDialog();
                    }
                });
            }
        });

        return convertView;
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(this.getContext());
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }
}
