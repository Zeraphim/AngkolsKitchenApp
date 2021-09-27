package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class PlaceOrder extends AppCompatActivity {

    Button confirmAddressButton;
    ImageButton backToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        confirmAddressButton = findViewById(R.id.confirmAddressButton);
        confirmAddressButton.setOnClickListener(view -> {
            startActivity(new Intent(PlaceOrder.this, AfterPurchase.class));
        });
        backToCartButton = findViewById(R.id.backToCartButton);
        backToCartButton.setOnClickListener(view -> {
            startActivity(new Intent(PlaceOrder.this, FoodCart.class));
        });
    }
}