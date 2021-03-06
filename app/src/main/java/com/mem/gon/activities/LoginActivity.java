package com.mem.gon.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.mem.gon.R;
import com.mem.gon.fragments.SignInFragment;
import com.mem.gon.fragments.SignUpFragment;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    TextView signIn, signUp;
    SignUpFragment lf;
    SignInFragment ls;

    CallbackManager callbackManager;

    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiClass.context = this;
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);

        if(Session.isUserSignedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("user_friends,email");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                showLoadingDialog();
                                try {
                                    final String uid = response.getJSONObject().getString("id");
                                    final String email = response.getJSONObject().getString("email");
                                    final String[] name = response.getJSONObject().getString("name").split(" ");
                                    ApiClass.facebookLogin(uid, email, name[0], name[1], new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            try {
                                                User user = new User(response.getString("email"), response.getString("first_name"), response.getString("last_name"));
                                                user.setId(response.getLong("id"));
                                                user.setFacebookUID(response.getString("facebook_uid"));
                                                Session.create(user);
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                                finish();
                                                hideLoadingDialog();
                                            } catch (JSONException e) {
                                                hideLoadingDialog();
                                                e.printStackTrace();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                            hideLoadingDialog();
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
                // Session.create(new User("mark.yehia@gmail.com", "Mark", "Morcos"));
                // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                // finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });

        if (getActionBar() != null) {
            getActionBar().hide();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        signIn = (TextView)findViewById(R.id.sign_in_layout);
        signUp = (TextView)findViewById(R.id.sign_up_layout);

        lf = new SignUpFragment();
        ls = new SignInFragment();

        signUp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_login, lf).commit();
            }
        });

        signIn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_login, ls).commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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