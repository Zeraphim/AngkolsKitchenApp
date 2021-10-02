package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GalleryMenu extends AppCompatActivity {

    Button btnLogOut;
    Button about_us;
    FirebaseAuth mAuth;
    ImageButton btnAccount;
    ImageButton btnHome;
    ImageButton btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_menu);

        btnAccount = findViewById(R.id.btnAccount);
        btnHome = findViewById(R.id.btnHome);
        btnCart = findViewById(R.id.btnCart);
        btnLogOut = findViewById(R.id.btnLogout);
        about_us = findViewById(R.id.about_us);
        about_us.setOnClickListener(view -> {
            startActivity(new Intent(GalleryMenu.this, AboutPage.class));
        });
        mAuth = FirebaseAuth.getInstance();

        btnLogOut.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(GalleryMenu.this, LoginActivity.class));
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(GalleryMenu.this, LoginActivity.class));
        }
    }

    public void openAccount (View view){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void openCart (View view){
        Intent intent = new Intent(this, FoodCart.class);
        startActivity(intent);
    }

    public void openHome (View view){
        Intent intent = new Intent(this, GalleryMenu.class);
        startActivity(intent);
    }

    public void openIndivProduct (View view){
        Intent intent = new Intent(this, IndividualProduct.class);
        startActivity(intent);

    }


}