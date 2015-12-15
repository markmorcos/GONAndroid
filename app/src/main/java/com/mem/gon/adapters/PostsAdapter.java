package com.mem.gon.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.Comment;
import com.mem.gon.models.Post;
import com.mem.gon.util.ApiClass;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mark on 27/11/15.
 */
public class PostsAdapter extends ArrayAdapter<Post> {
    ProgressDialog dialog;
    public PostsAdapter(Context context, int resource, Post[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Post post = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_posts, parent, false);
        }

        final EditText commentEditText = (EditText) convertView.findViewById(R.id.edit_text_comment_text);
        Button addComment = (Button) convertView.findViewById(R.id.button_add_comment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                ApiClass.commentAComment(Session.getUser().getId(), post.getId(), commentEditText.getText().toString(), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideLoadingDialog();
                        commentEditText.setText("");
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

        CircleImageView postPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_post_picture);
        postPicture.setImageResource(R.drawable.picture);
        TextView postText = (TextView) convertView.findViewById(R.id.text_view_post_text);
        postText.setText(post.getText());
        LinearLayout comments = (LinearLayout) convertView.findViewById(R.id.linear_layout_comments);
        for (Comment comment : post.getComments()) {

            View commentView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_comments, parent, false);
            CircleImageView commentPicture = (CircleImageView) commentView.findViewById(R.id.circle_image_view_comment_picture);
            commentPicture.setImageResource(R.drawable.picture);
            TextView commentText = (TextView) commentView.findViewById(R.id.text_view_comment_text);
            commentText.setText(comment.getUser().getName() + ": " + comment.getText());
            comments.addView(commentView);
        }

        return convertView;
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }
}

