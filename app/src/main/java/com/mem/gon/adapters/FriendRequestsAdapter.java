package com.mem.gon.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.activities.MainActivity;
import com.mem.gon.activities.ProfileActivity;
import com.mem.gon.fragments.CurrentFriendsFragment;
import com.mem.gon.fragments.FriendRequestsFragment;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

/**
 * Created by mark on 27/11/15.
 */
public class FriendRequestsAdapter extends ArrayAdapter<User> {
    ProgressDialog dialog;
    public FriendRequestsAdapter(Context context, int resource, User[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_friend_requests, parent, false);
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
        Button acceptButton = (Button) convertView.findViewById(R.id.button_friend_accept);
        Button rejectButton = (Button) convertView.findViewById(R.id.button_friend_reject);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                ApiClass.acceptRejectFriend(user.getFriendId(), true, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ((MaterialNavigationDrawer) getContext()).setFragmentChild(new CurrentFriendsFragment(), "Current Friends");
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

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                ApiClass.acceptRejectFriend(user.getFriendId(), false, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ((MaterialNavigationDrawer) getContext()).setFragmentChild(new FriendRequestsFragment(), "Friend Requests");
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
