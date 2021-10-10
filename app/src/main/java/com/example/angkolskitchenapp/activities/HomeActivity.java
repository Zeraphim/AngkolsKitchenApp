package com.example.angkolskitchenapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.angkolskitchenapp.GalleryMenu;
import com.example.angkolskitchenapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    //ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        //progressBar = findViewById(R.id.progressBar);
        //progressBar.setVisibility(View.GONE);

        if(auth.getCurrentUser() != null) {
            //progressBar.setVisibility(View.VISIBLE);

            startActivity(new Intent(com.example.angkolskitchenapp.activities.HomeActivity.this, GalleryMenu.class));

            Toast.makeText(this, "Please wait you are already logged in",Toast.LENGTH_SHORT);

            finish();
        }
    }
/*
    public void login(View view) {
        startActivity(new Intent(com.example.angkolskitchenapp.activities.HomeActivity.this, LoginActivity.class));
    }

    public void registration(View view) {
        startActivity(new Intent(com.example.angkolskitchenapp.activities.HomeActivity.this, RegistrationActivity.class));
    }

 */
}