package com.mem.gon.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.adapters.PostsAdapter;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.Comment;
import com.mem.gon.models.Post;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class NewsFeedFragment extends Fragment {
    ProgressDialog dialog;
    public NewsFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        final ListView postsList = (ListView) rootView.findViewById(R.id.list_view_news_feed);
        final Button post = (Button) rootView.findViewById(R.id.button_add_post);
        final EditText postText = (EditText) rootView.findViewById(R.id.edit_text_post_text);
        User mark = new User("mark@gmail.com", "Mark", "Morcos");
        User mayar = new User("mayar@gmail.com", "Mayar", "Ali");
        Comment comment1 = new Comment(mayar, "Mayar made this comment on the first post");
        Comment comment2 = new Comment(mayar, "Mayar made this comment, too, on the first post");
        Comment comment3 = new Comment(mark, "Mark made this comment on the second post");
        ArrayList<Comment> comments1 = new ArrayList<>();
        comments1.add(comment1);
        comments1.add(comment2);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                ApiClass.postAPost(Session.getUser().getId(),0 , postText.getText().toString() ,new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideLoadingDialog();
                        postText.setText("");
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
        ArrayList<Comment> comments2 = new ArrayList<>();
        comments2.add(comment3);
        showLoadingDialog();
        //Post[] postsArray;
        ApiClass.getNewsFeed(Session.getUser().getId(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                   final Post[] postsArray = new Post[response.length()];
                    for (int i = 0; i < response.length(); ++i) {
                        JSONObject currentPost = response.getJSONObject(i);
                        Post post = new Post();
                        post.setId(currentPost.getLong("id"));
                        post.setUser(User.fromJSON(currentPost.getJSONObject("user")));
                        ArrayList<Comment> comments = new ArrayList<Comment>();
                        for(int j = 0; j < currentPost.getJSONArray("comments").length(); ++j){
                            User commentWriter = User.fromJSON(currentPost.getJSONArray("comments").getJSONObject(j).getJSONObject("user"));
                            comments.add(new Comment(commentWriter, currentPost.getJSONArray("comments").getJSONObject(j).getString("text")));
                        }
                        post.setComments(comments);
                        post.setText(post.getUser().getName() + ": " + currentPost.getString("text"));
                        if(currentPost.getLong("other_user_id") != 0) {
                            post.setOtherUser(User.fromJSON(currentPost.getJSONObject("other_user")));
                            post.setText(post.getUser().getName() + " -> " + post.getOtherUser().getName() + ": " + currentPost.getString("text"));
                        }
                        postsArray[i] = post;
                        postsList.setAdapter(new PostsAdapter(getActivity(), R.layout.adapter_posts, postsArray));
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                hideLoadingDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideLoadingDialog();
            }
        });
        return rootView;
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }
}
