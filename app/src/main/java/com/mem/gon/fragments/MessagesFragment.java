package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mem.gon.R;
import com.mem.gon.adapters.MessagesAdapter;
import com.mem.gon.models.User;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;

public class MessagesFragment extends Fragment {

    public MessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_messages, container, false);
        ListView messagesList = (ListView) rootView.findViewById(R.id.list_view_messages);
        final User[] friendsArray = new User[] {
                new User("ebram@gmail.com", "Ebram", "Mitry"),
                new User("mayar@gmail.com", "Mayar", "Ali"),
        };
        messagesList.setAdapter(new MessagesAdapter(getActivity(), R.layout.adapter_messages, friendsArray));
        messagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(new MessageFragment(), "You and " + friendsArray[i].getName());
            }
        });
        return rootView;
    }
}
