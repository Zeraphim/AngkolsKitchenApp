package com.example.angkolskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class IndividualProduct extends AppCompatActivity {
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product);

        imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(view -> {
            startActivity(new Intent(IndividualProduct.this, GalleryMenu.class));
        });

    }


}