package com.mem.gon.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mem.gon.R;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_settings, container, false);
        final EditText oldPassword = (EditText) rootView.findViewById(R.id.old_password);
        final EditText newPassword = (EditText) rootView.findViewById(R.id.new_password);
        final EditText newPasswordConfirmation = (EditText) rootView.findViewById(R.id.new_password_confirmation);
        Button saveButton = (Button) rootView.findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!newPassword.getText().toString().equals(newPasswordConfirmation.getText().toString())) {
                    Toast.makeText(getActivity(), "New password and confirmation don't match!", Toast.LENGTH_LONG).show();
                } else if(oldPassword.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Old password should not be empty!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Password updated successfully!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}
