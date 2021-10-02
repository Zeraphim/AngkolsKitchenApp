package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class PurchaseHistory extends AppCompatActivity {
    ImageButton account_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        account_return = findViewById(R.id.account_return);
        account_return.setOnClickListener(view -> {
            startActivity(new Intent(PurchaseHistory.this, Account.class));
        });
    }
}