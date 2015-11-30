package com.mem.gon.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mem.gon.R;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;

public class DetailsActivity extends Activity {

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
                    String email = getIntent().getStringExtra("email");
                    Session.create(new User(email, firstName.getText().toString(), lastName.getText().toString()));
                    startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
    }
}
