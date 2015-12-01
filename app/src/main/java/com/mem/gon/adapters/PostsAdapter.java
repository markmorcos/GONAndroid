package com.mem.gon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.models.Comment;
import com.mem.gon.models.Post;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by mark on 27/11/15.
 */
public class PostsAdapter extends ArrayAdapter<Post> {

    public PostsAdapter(Context context, int resource, Post[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Post post = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_posts, parent, false);
        }
        CircleImageView postPicture = (CircleImageView) convertView.findViewById(R.id.circle_image_view_post_picture);
        postPicture.setImageResource(R.drawable.picture);
        TextView postText = (TextView) convertView.findViewById(R.id.text_view_post_text);
        postText.setText(post.getText());
        LinearLayout comments = (LinearLayout) convertView.findViewById(R.id.linear_layout_comments);
        for (Comment comment : post.getComments()) {
            View commentView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_comments, parent, false);
            CircleImageView commentPicture = (CircleImageView) commentView.findViewById(R.id.circle_image_view_comment_picture);
            commentPicture.setImageResource(R.drawable.picture);
            TextView commentText = (TextView) commentView   .findViewById(R.id.text_view_comment_text);
            commentText.setText(comment.getText());
            comments.addView(commentView);
        }
        return convertView;
    }
}
