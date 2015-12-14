package com.mem.gon.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.FacebookSdk;
import com.mem.gon.R;
import com.mem.gon.activities.MainActivity;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SignInFragment extends Fragment {
    Button login;
    TextView email;
    TextView password;
    View view;

    ProgressDialog dialog;

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
                    showLoadingDialog();
                    ApiClass.login(email.getText().toString(), password.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response);
                                if (json.optBoolean("error")) {
                                    Toast.makeText(getActivity(), json.getString("error_message"), Toast.LENGTH_LONG).show();
                                } else {
                                    User user = new User(json.getString("email"), json.getString("first_name"), json.getString("last_name"));
                                    user.setId(json.getLong("id"));
                                    user.setPicture(json.getString("picture"));
                                    user.setLatitude(json.getDouble("latitude"));
                                    user.setLongitude(json.getDouble("longitude"));
                                    user.setFacebookUID(json.getString("facebook_uid"));
                                    Session.create(user);
                                    getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                                    getActivity().finish();
                                }
                            } catch (JSONException e) {
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
                }

            }
        });
        return view;
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