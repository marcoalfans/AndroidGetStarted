package com.lasakpedia.simplecarwash;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    AppCompatTextView nav_Fullname, nav_Email_Address;
    AppCompatImageView nav_Photo;

    FloatingActionButton fab, fab1, fab2, fab3;
    Animation fab_open, fab_close, fab_clock, fab_anticlock;
    AppCompatTextView tv1, tv2, tv3;
    Boolean isOpen = false;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";


    DatabaseReference refLoadNavProfil;

    @Override //Mulai onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getUsernameLocal();

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        tv1 = findViewById(R.id.tv_fabSelectCar);
        tv2 = findViewById(R.id.tv_fabServices);
        tv3 = findViewById(R.id.tv_fabArrivalTime);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                if (isOpen) {
                    tv1.setVisibility(View.INVISIBLE);
                    tv2.setVisibility(View.INVISIBLE);
                    tv3.setVisibility(View.INVISIBLE);
                    fab1.startAnimation(fab_close);
                    fab2.startAnimation(fab_close);
                    fab3.startAnimation(fab_close);
                    fab.startAnimation(fab_anticlock);
                    fab1.setClickable(false);
                    fab2.setClickable(false);
                    fab3.setClickable(false);
                    isOpen = false;
                } else {
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    tv3.setVisibility(View.VISIBLE);
                    fab1.startAnimation(fab_open);
                    fab2.startAnimation(fab_open);
                    fab3.startAnimation(fab_open);
                    fab.startAnimation(fab_clock);
                    fab1.setClickable(true);
                    fab2.setClickable(true);
                    fab3.setClickable(true);
                    isOpen = true;
                }
            }
        });


        refLoadNavProfil = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        /*refLoadNavProfil.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("full_name")) {
                    nav_Fullname.setText(dataSnapshot.child("full_name").getValue().toString());
                } else {
                    nav_Fullname.setText("Your Name");
                }
                if (dataSnapshot.hasChild("email_address")) {
                    nav_Email_Address.setText(dataSnapshot.child("email_address").getValue().toString());
                } else {
                    nav_Email_Address.setText("yourmail@mail.com");
                }
                if (dataSnapshot.hasChild("url_photo_profile")) {
                    Picasso.with(HomeAct.this).load(dataSnapshot.child("url_photo_profile").getValue().toString())
                            .centerCrop().fit().into(nav_Photo);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });*/

    }//selesai onCreate


    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
