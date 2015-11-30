package com.mem.gon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.activities.DetailsActivity;

public class SignUpFragment extends Fragment {
    TextView next;
    View view;
    EditText newEmail;

    public SignUpFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.removeAllViews();
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        next = (TextView)view.findViewById(R.id.next);
        newEmail = (EditText)view.findViewById(R.id.new_email);
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(newEmail.getText().length() == 0) {
                    newEmail.setError("email can't be blank");
                }
                else if(!newEmail.getText().toString().trim().matches(emailPattern)) {
                    newEmail.setError("invalid email address");
                }
                else {
                    Intent intent = new Intent(getActivity(), DetailsActivity.class);
                    intent.putExtra("email", newEmail.getText().toString());
                    getActivity().startActivity(intent);
                    getActivity().finish();
                }

            }
        });
        return view;
    }
}