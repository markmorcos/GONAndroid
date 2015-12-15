package com.mem.gon.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mem.gon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mem.gon.adapters.PostsAdapter;
import com.mem.gon.helpers.Session;
import com.mem.gon.models.Comment;
import com.mem.gon.models.Post;
import com.mem.gon.models.User;
import com.mem.gon.util.ApiClass;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity implements OnMapReadyCallback {

    EditText name;
    TextView nameview;
    Button done, cancel;
    Button name_button;
    CircleImageView image;
    Toolbar mActionBarToolbar;
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    //ADDED
    private String filemanagerstring;

    ProgressDialog dialog;

    User currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        long id = getIntent().getLongExtra("id", 0);
        name_button = (Button) findViewById(R.id.change_name);
        name = (EditText) findViewById(R.id.profile_name);
        name.setText(Session.getUser().getName());
        nameview = (TextView) findViewById(R.id.profile_name_view);
        nameview.setText(Session.getUser().getName());
        done = (Button) findViewById(R.id.done_name);
        cancel = (Button) findViewById(R.id.cancel_change);
        image = (CircleImageView) findViewById(R.id.profile_picture);

        if (id == 0 || id == Session.getUser().getId()) {
            currentUser = Session.getUser();

            if (Session.getUser().getPicture() != null) {
                Picasso.with(this).load(Session.getUser().getPicture()).placeholder(R.drawable.picture).into(image);
            }
            image.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {

                    // in onCreate or any event where your want the user to
                    // select a file
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra
                            (
                                    Intent.EXTRA_INITIAL_INTENTS,
                                    new Intent[] { takePhotoIntent }
                            );
                    startActivityForResult(Intent.createChooser(intent,
                            "Select Picture"), SELECT_PICTURE);
                }
            });


            name_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name.setText(nameview.getText());
                    name.setVisibility(View.VISIBLE);
                    nameview.setVisibility(View.GONE);
                    name_button.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    done.setVisibility(View.VISIBLE);

                    // Show soft keyboard for the user to enter the value.


                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nameview.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    name_button.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    done.setVisibility(View.GONE);
                    // Show soft keyboard for the user to enter the value.


                }
            });

            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String [] fullName = name.getText().toString().split(" ");
                    final String firstName = fullName[0];
                    final String lastName = fullName[1];
                    try {
                        JSONObject params = new JSONObject();
                        params.put("id", Session.getUser().getId());
                        params.put("first_name", firstName);
                        params.put("last_name", lastName);
                        showLoadingDialog();
                        ApiClass.changeName(Session.getUser().getId(), firstName, lastName, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.optBoolean("error")) {
                                        Toast.makeText(ProfileActivity.this, response.getString("error_message"), Toast.LENGTH_LONG).show();
                                    } else {
                                        //System.out.println(" HERE ");
                                        Session.getUser().setFirstName(firstName);
                                        Session.getUser().setLastName(lastName);
                                        nameview.setText(Session.getUser().getName());
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                hideLoadingDialog();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                hideLoadingDialog();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    nameview.setVisibility(View.VISIBLE);
                    name.setVisibility(View.GONE);
                    name_button.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    done.setVisibility(View.GONE);
                    // Show soft keyboard for the user to enter the value.
                }
            });




        } else {
            final Button addFriend = (Button) findViewById(R.id.add_friend);
            final Button unfriend = (Button) findViewById(R.id.remove_friend);
            done.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            name_button.setVisibility(View.GONE);

            //TODO: Check friends
            addFriend.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    addFriend.setVisibility(View.GONE);
                    unfriend.setVisibility(View.VISIBLE);
                }
            });


            unfriend.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    unfriend.setVisibility(View.GONE);
                    addFriend.setVisibility(View.VISIBLE);
                }
            });

        }





        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Profile");


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final ListView postsList = (ListView) findViewById(R.id.list_view_news_feed);
        User mark = new User("mark@gmail.com", "Mark", "Morcos");
        User mayar = new User("mayar@gmail.com", "Mayar", "Ali");
        Comment comment1 = new Comment(mayar, "Mayar made this comment on the first post");
        Comment comment2 = new Comment(mayar, "Mayar made this comment, too, on the first post");
        Comment comment3 = new Comment(mark, "Mark made this comment on the second post");
        ArrayList<Comment> comments1 = new ArrayList<>();
        comments1.add(comment1);
        comments1.add(comment2);
        ArrayList<Comment> comments2 = new ArrayList<>();
        comments2.add(comment3);
        final Post[] postsArray = new Post[] {
                new Post(mark, null, "This is a post Mark Morcos posted!", 0, 0, "", comments1),
                new Post(mayar, mark, "This is a post Mayar posted on Mark's timeline!.", 0, 0, "", comments2)
        };
        postsList.setAdapter(new PostsAdapter(this, R.layout.adapter_posts, postsArray));

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                //OI FILE Manager
                filemanagerstring = selectedImageUri.getPath();

                //MEDIA GALLERY
                selectedImagePath = getPath(selectedImageUri);

                //DEBUG PURPOSE - you can delete this if you want
                if(selectedImagePath!=null)
                    System.out.println(selectedImagePath);
                else System.out.println("selectedImagePath is null");
                if(filemanagerstring!=null)
                    System.out.println(filemanagerstring);
                else System.out.println("filemanagerstring is null");

                //NOW WE HAVE OUR WANTED STRING
                if(selectedImagePath!=null) {
                    System.out.println("selectedImagePath is the right one for you!");
                    image.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
                }
                else {
                    System.out.println("filemanagerstring is the right one for you!");
                    image.setImageBitmap(BitmapFactory.decodeFile(filemanagerstring));
                }
            }
        }
    }

    void showLoadingDialog() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.show();
    }

    void hideLoadingDialog() {
        dialog.dismiss();
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if(cursor!=null)
        {
            //HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            //THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

    }

}

