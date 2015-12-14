package com.mem.gon.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends Activity {

    ProgressDialog dialog;

    public DetailsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Button signUp = (Button) findViewById(R.id.signup_button);
        final EditText firstName = (EditText) findViewById(R.id.first_name);
        final EditText lastName = (EditText) findViewById(R.id.last_name);
        final EditText password = (EditText) findViewById(R.id.new_password);
        final EditText confirmPassword = (EditText) findViewById(R.id.new_password_confirmation);

        signUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (firstName.getText().length() == 0) {
                    firstName.setError("First Name can't be blank");
                }
                else if (lastName.getText().length() == 0) {
                    lastName.setError("Last Name can't be blank");
                }
                else if (password.getText().length() < 8) {
                    password.setError("Your password should be at least 8 characters");
                }
                else if (!confirmPassword.getText().toString().equals(password.getText().toString())) {
                    confirmPassword.setError("Password doesn't match");
                }
                else {
                    showLoadingDialog();
                    final String email = getIntent().getStringExtra("email");
                    ApiClass.signUp(email, password.getText().toString(), firstName.getText().toString(), lastName.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response);
                                if (json.optBoolean("error")) {
                                    Toast.makeText(DetailsActivity.this, json.getString("error_message"), Toast.LENGTH_LONG).show();
                                } else {
                                    ApiClass.login(email, password.getText().toString(), new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            try {
                                                JSONObject json = new JSONObject(response);
                                                if (json.optBoolean("error")) {
                                                    Toast.makeText(DetailsActivity.this, json.getString("error_message"), Toast.LENGTH_LONG).show();
                                                } else {
                                                    User user = new User(json.getString("email"), json.getString("first_name"), json.getString("last_name"));
                                                    user.setId(json.getLong("id"));
                                                    Session.create(user);
                                                    startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                                                    finish();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            hideLoadingDialog();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(DetailsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                            hideLoadingDialog();
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            hideLoadingDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            hideLoadingDialog();
                        }
                    });
                }

            }
        });
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }
}
