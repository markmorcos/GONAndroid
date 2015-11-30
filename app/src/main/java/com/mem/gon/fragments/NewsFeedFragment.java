package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.gon.R;

public class NewsFeedFragment extends Fragment {

    public NewsFeedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_news_feed, null);
        return rootView;
    }
}
