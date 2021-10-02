package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Account extends AppCompatActivity {
    ImageButton back_GalleryMenu;
    ImageButton btn_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn_history = findViewById(R.id.btn_history);
        back_GalleryMenu = findViewById(R.id.back_GalleryMenu);
        back_GalleryMenu.setOnClickListener(view -> {
            startActivity(new Intent(Account.this, GalleryMenu.class));
        });



    }

    public void openHistory (View view){
        Intent intent = new Intent(this, PurchaseHistory.class);
        startActivity(intent);
    }
}