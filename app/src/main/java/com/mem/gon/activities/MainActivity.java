package com.mem.gon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.mem.gon.R;
import com.mem.gon.fragments.NewsFeedFragment;
import com.mem.gon.fragments.NotificationsFragment;

import java.util.Objects;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;

public class MainActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

    @Override
    public void init(Bundle bundle) {
        // set account
        addAccount(new MaterialAccount(getResources(), "Mark Morcos", "mark.yehia@gmail.com", R.drawable.photo, R.drawable.bamboo));

        // set listener
        this.setAccountListener(this);

        // create sections
        this.addSection(newSection("News Feed", new NewsFeedFragment()));
        this.addSection(newSection("Notifications", new NotificationsFragment()));
//        this.addSection(newSection("Activities", new ActivitiesFragment()));
//        this.addSection(newSection("Messages", new MessagesFragment()));
//        this.addSection(newSection("Friends", new FriendsFragment()));
//        this.addSection(newSection("Meetings", new MeetingsFragment()));

        // create bottom sections
//        this.addBottomSection(newSection("Edit Profile", R.drawable.ic_setting_dark, new EditProfileFragment()));
        this.addBottomSection(newSection("Logout", R.drawable.ic_setting_dark, target));
    }

    @Override
    public void onAccountOpening(MaterialAccount materialAccount) {
        Toast.makeText(this, "Account Settings", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onChangeAccount(MaterialAccount materialAccount) {

    }

    MaterialSectionListener target = new MaterialSectionListener() {
        @Override
        public void onClick(MaterialSection materialSection) {
            if (materialSection.getTitle().equals("Logout")) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        }
    };
}

/*public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavigationDrawerTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            String title = activityInfo.loadLabel(getPackageManager()).toString();
            toolbar.setTitle(title);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mNavigationDrawerTitles = getResources().getStringArray(R.array.navigation_drawer_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.mipmap.drawer_shadow, GravityCompat.START);
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, mNavigationDrawerTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch(item.getItemId()) {
            case R.id.action_websearch:
                final EditText search = (EditText) findViewById(R.id.search);
                if (search.getVisibility() == View.GONE) {
                    search.setVisibility(View.VISIBLE);
                } else {
                    search.setVisibility(View.GONE);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        args.putInt(NewsFeedFragment.ARG_NAVIGATION_DRAWER_POSITION, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(mNavigationDrawerTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}*/