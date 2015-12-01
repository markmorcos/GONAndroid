package com.mem.gon.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mem.gon.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(mActionBarToolbar);
        getSupportActionBar().setTitle("Profile");


        TextView text = new TextView(this);
        text.setText("Section");
        text.setGravity(Gravity.CENTER);
        name_button = (Button) findViewById(R.id.change_name);
        name = (EditText) findViewById(R.id.profile_name);
        nameview = (TextView) findViewById(R.id.profile_name_view);
        done = (Button) findViewById(R.id.done_name);
        cancel = (Button) findViewById(R.id.cancel_change);
        nameview.setText(name.getText());
        image = (CircleImageView) findViewById(R.id.profile_picture);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
                nameview.setText(name.getText());
                nameview.setVisibility(View.VISIBLE);
                name.setVisibility(View.GONE);
                name_button.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.GONE);
                done.setVisibility(View.GONE);
                // Show soft keyboard for the user to enter the value.


            }
        });
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

    //UPDATED!
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

