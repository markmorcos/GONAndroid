package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mem.gon.R;
import com.mem.gon.adapters.MessageAdapter;
import com.mem.gon.models.Message;
import com.mem.gon.models.User;

public class MessageFragment extends Fragment {

    public MessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_message, container, false);
        ListView messagesList = (ListView) rootView.findViewById(R.id.list_view_message);
        User mark = new User("mark@gmail.com", "Mark", "Morcos");
        User mayar = new User("mayar@gmail.com", "Mayar", "Ali");
        final Message[] messagesArray = new Message[] {
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
                new Message(mark, mayar, "I am the sender! This is a long message to test the chatting feature in our application."),
                new Message(mayar, mark, "You are the receiver! This is a long message to test the chatting feature in our application."),
        };
        messagesList.setAdapter(new MessageAdapter(getActivity(), R.layout.adapter_messages, messagesArray));
        return rootView;
    }
}
