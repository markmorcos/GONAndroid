package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mem.gon.R;
import com.mem.gon.adapters.PostsAdapter;
import com.mem.gon.models.Comment;
import com.mem.gon.models.Post;
import com.mem.gon.models.User;

import java.util.ArrayList;

public class NewsFeedFragment extends Fragment {

    public NewsFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_news_feed, container, false);
        final ListView postsList = (ListView) rootView.findViewById(R.id.list_view_news_feed);
        User mark = new User("mark@gmail.com", "Mark", "Morcos");
        User mayar = new User("mayar@gmail.com", "Mayar", "Ali");

        Comment comment1 = new Comment(mayar, "Mayar made this comment on the first post");
        Comment comment2 = new Comment(mayar, "Mayar made this comment, too, on the first post");
        Comment comment3 = new Comment(mark, "Mark made this comment on the second post");
        ArrayList<Comment> comments1 = new ArrayList<>();
        comments1.add(comment1);
        comments1.add(comment2);
        ArrayList<Comment> comments2 = new ArrayList<>();
        comments2.add(comment3);
        final Post[] postsArray = new Post[] {
                new Post(mark, null, "This is a post Mark Morcos posted!", 0, 0, "", comments1),
                new Post(mayar, mark, "This is a post Mayar posted on Mark's timeline!.", 0, 0, "", comments2)
        };
        postsList.setAdapter(new PostsAdapter(getActivity(), R.layout.adapter_posts, postsArray));
        return rootView;
    }
}
