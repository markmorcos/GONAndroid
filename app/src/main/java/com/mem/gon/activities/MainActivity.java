package com.mem.gon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.mem.gon.R;
import com.mem.gon.fragments.CurrentFriendsFragment;
import com.mem.gon.fragments.MeetingsFragment;
import com.mem.gon.fragments.MessagesFragment;
import com.mem.gon.fragments.NewsFeedFragment;
import com.mem.gon.fragments.SettingsFragment;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.User;
import com.squareup.picasso.Picasso;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;

public class MainActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

    @Override
    public void init(Bundle bundle) {
        if (!Session.isUserSignedIn()) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }
        // set account
        User user = Session.getUser();
        addAccount(new MaterialAccount(getResources(), user.getName(), user.getEmail(), R.drawable.picture, R.drawable.bamboo));
        // set listener
        setAccountListener(this);

        // create sections
        addSection(newSection("News Feed", R.drawable.ic_public_black_24dp, new NewsFeedFragment()).setNotifications(10));
        addSection(newSection("Messages", R.drawable.ic_message_black_24dp, new MessagesFragment()).setNotifications(20));
        addSection(newSection("Current Friends", R.drawable.ic_wc_black_24dp, new CurrentFriendsFragment()).setNotifications(30));
        addSection(newSection("Upcoming Meetings", R.drawable.ic_group_black_24dp, new MeetingsFragment()).setNotifications(40));

        // create bottom sections
        addSection(newSection("UPDATE LOCATION", R.drawable.ic_location_on_black_24dp, new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {
                Toast.makeText(MainActivity.this, "Your location has been updated!", Toast.LENGTH_LONG).show();
            }
        }));
        addBottomSection(newSection("Settings", R.drawable.ic_setting_light, new SettingsFragment()));
        addBottomSection(newSection("Logout", R.drawable.ic_home_black_24dp, new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection materialSection) {
                Session.destroy();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("id", Session.getUser().getId());
        startActivity(intent);
    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {
    }
}