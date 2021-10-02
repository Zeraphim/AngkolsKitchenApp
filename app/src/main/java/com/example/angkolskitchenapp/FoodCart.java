package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class FoodCart extends AppCompatActivity {

    Button checkOutButton;
    ImageButton backToGalleryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cart);
        checkOutButton = findViewById(R.id.checkOutButton);
        checkOutButton.setOnClickListener(view -> {
            startActivity(new Intent(FoodCart.this, PlaceOrder.class));
        });
        backToGalleryButton = findViewById(R.id.backToGalleryButton);
        backToGalleryButton.setOnClickListener(view -> {
            startActivity(new Intent(FoodCart.this, GalleryMenu.class));
        });

    }
}