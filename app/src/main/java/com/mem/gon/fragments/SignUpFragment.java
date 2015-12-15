package com.mem.gon.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.activities.DetailsActivity;
import com.mem.gon.util.ApiClass;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpFragment extends Fragment {
    TextView next;
    View view;
    EditText newEmail;

    ProgressDialog dialog;

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
                    showLoadingDialog();
                    ApiClass.checkEmail(newEmail.getText().toString(), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (response.optBoolean("error")) {
                                Toast.makeText(getActivity(), "Email has already been taken", Toast.LENGTH_LONG).show();
                            } else {
                                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                                intent.putExtra("email", newEmail.getText().toString());
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            }
                            hideLoadingDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.networkResponse.statusCode == 422) {
                                Toast.makeText(getActivity(), "Email has already been taken", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
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