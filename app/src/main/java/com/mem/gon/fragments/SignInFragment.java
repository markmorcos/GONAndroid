package com.mem.gon.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.mem.gon.R;
import com.mem.gon.activities.MainActivity;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;

public class SignInFragment extends Fragment {
    Button login;
    TextView email;
    TextView password;
    View view;

    public SignInFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_sign_in, container, false);
        login = (Button)  view.findViewById(R.id.login_button);
        email = (TextView) view.findViewById(R.id.email);
        password = (TextView) view.findViewById(R.id.password);
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("invalid email address");
                }
                else if (email.getText().length() == 0) {
                    email.setError("email can't be blank");
                }
                else if (password.getText().length() == 0) {
                    password.setError("password can't be blank");
                }
                else {
                    Session.create(new User(email.getText().toString(), "Mark", "Morcos"));
                    getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }

            }
        });
        return view;
    }
}