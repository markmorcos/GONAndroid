package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.gon.R;

public class ActivitiesFragment extends Fragment {

    public ActivitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView  = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_activities, null);
        return rootView;
    }
}
