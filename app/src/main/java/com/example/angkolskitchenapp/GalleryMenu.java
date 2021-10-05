package com.example.angkolskitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class GalleryMenu extends AppCompatActivity {

    Button btnLogOut;
    Button about_us;
    FirebaseAuth mAuth;
    ImageButton btnAccount;
    ImageButton btnHome;
    ImageButton btnCart;
    RecyclerView recyclerView;
    ArrayList<GalleryMenuItem> galleryItemArrayList;
    GalleryMenuAdapter myAdapter;
    String[] name;
    int[] img;

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

        btnAccount.setOnClickListener(view -> {
            startActivity(new Intent(GalleryMenu.this, Account.class));
        });
        btnHome.setOnClickListener(view -> {
            startActivity(new Intent(GalleryMenu.this, GalleryMenu.class));
        });
        btnCart.setOnClickListener(view -> {
            startActivity(new Intent(GalleryMenu.this, FoodCart.class));
        });
        about_us.setOnClickListener(view -> {
            startActivity(new Intent(GalleryMenu.this, AboutPage.class));
        });



        recyclerView = findViewById(R.id.gallerymenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        galleryItemArrayList = new ArrayList<GalleryMenuItem>();
        myAdapter = new GalleryMenuAdapter(this , galleryItemArrayList);
        recyclerView.setAdapter(myAdapter);



        name = new String[]{
                "Creamy Fettuccine Carbonara",
                "Spaghetti and Meatballs",
                "Chicken Pesto pasta",
                "Chicken Cordon Bleu"
        };

        img = new int[]{
          R.drawable.a,
          R.drawable.b,
          R.drawable.e,
          R.drawable.d

        };

        getData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(GalleryMenu.this, LoginActivity.class));
        }
    }

    private void getData(){
        for (int i = 0;i<name.length;i++){
            GalleryMenuItem galleryitem = new GalleryMenuItem(name[i], img[i]);
            galleryItemArrayList.add(galleryitem);

        }
        myAdapter.notifyDataSetChanged();
    }

 public void indivBTN(View view){
        Intent intent= new Intent(this, IndividualProduct.class);
        startActivity(intent);

 }

}