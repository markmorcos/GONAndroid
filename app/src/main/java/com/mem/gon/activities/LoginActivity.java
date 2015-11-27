package com.mem.gon.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;

import com.mem.gon.R;
import com.mem.gon.fragments.SignInFragment;
import com.mem.gon.fragments.SignUpFragment;

public class LoginActivity extends AppCompatActivity {
    TextView signIn, signUp;
    SignUpFragment lf;
    SignInFragment ls;

    public LoginActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
}