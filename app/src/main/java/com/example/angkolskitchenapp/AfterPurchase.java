package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AfterPurchase extends AppCompatActivity {

    ImageButton backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_purchase);
        backToMainButton = findViewById(R.id.backToMainButton);
        backToMainButton.setOnClickListener(view -> {
            startActivity(new Intent(AfterPurchase.this, MainActivity.class));
        });
    }
}