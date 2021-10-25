package com.example.angkolskitchenapp.ui.category;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.angkolskitchenapp.GalleryMenu;
import com.example.angkolskitchenapp.R;
import com.example.angkolskitchenapp.activities.DetailedActivity;
import com.example.angkolskitchenapp.model.UserModel;
import com.example.angkolskitchenapp.model.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HealthFragment extends Fragment {

    TextView calories, carbs, protein, fat, txtCarbsPercentage, txtProteinPercentage, txtFatPercentage;

    FirebaseAuth auth;
    FirebaseDatabase database;

    Button reset;

    FirebaseFirestore firestore;


    ViewAllModel viewAllModel = null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        int textSize = 75;

        View root = inflater.inflate(R.layout.fragment_health, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        calories = root.findViewById(R.id.val_calories);
        carbs = root.findViewById(R.id.val_carbs);
        protein = root.findViewById(R.id.val_protein);
        fat = root.findViewById(R.id.val_fat);

        txtCarbsPercentage = root.findViewById(R.id.percentage_carbs);
        txtProteinPercentage = root.findViewById(R.id.percentage_protein);
        txtFatPercentage = root.findViewById(R.id.percentage_fat);

        // TextView Values

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel userModel = snapshot.getValue(UserModel.class);

                int totalCalories = (userModel.getCarbs() * 4) + (userModel.getProtein() * 4) + (userModel.getFat() * 9);

                calories.setText(String.valueOf(totalCalories));
                carbs.setText(String.valueOf(userModel.getCarbs() + "g"));
                protein.setText(String.valueOf(userModel.getProtein() + "g"));
                fat.setText(String.valueOf(userModel.getFat() + "g"));

                // Percentage computation


                double carbsP = ((double) (userModel.getCarbs() * 4) / totalCalories) * 100;
                double proteinP = ((double) (userModel.getProtein() * 4) / totalCalories) * 100;
                double fatP = ((double) (userModel.getFat() * 9) / totalCalories) * 100;

                double carbsPercentage = Math.round(carbsP * 100.0) / 100.0;
                double proteinPercentage = Math.round(proteinP * 100.0) / 100.0;
                double fatPercentage = Math.round(fatP * 100.0) / 100.0;

                String carbsPercentageFinal = String.valueOf(carbsPercentage + "%");
                String proteinPercentageFinal = String.valueOf(proteinPercentage + "%");
                String fatPercentageFinal = String.valueOf(fatPercentage + "%");

                txtCarbsPercentage.setText(carbsPercentageFinal);
                txtProteinPercentage.setText(proteinPercentageFinal);
                txtFatPercentage.setText(fatPercentageFinal);


                // Pie Chart

                AnimatedPieView mAnimatedPieView = root.findViewById(R.id.animatedPieView);
                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                config.startAngle(-90)// Starting angle offset
                        .addData(new SimplePieInfo(userModel.getCarbs(), Color.parseColor("#8A6FDF"), "Carbs")).drawText(true).textSize(textSize).canTouch(true)
                        .addData(new SimplePieInfo(userModel.getFat(), Color.parseColor("#FF5765"), "Fat")).drawText(true).textSize(textSize).canTouch(true)
                        .addData(new SimplePieInfo(userModel.getProtein(), Color.parseColor("#A8E10C"), "Protein")).drawText(true).textSize(textSize).canTouch(true)
                        .duration(2000);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
                mAnimatedPieView.applyConfig(config);
                mAnimatedPieView.start();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        reset = root.findViewById(R.id.reset_btn);

        reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetHealth();
            }
        });



        return root;
    }

    public void resetHealth() {

        // Adding health tracker variables to user

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel userModel = snapshot.getValue(UserModel.class);

                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("calories").setValue(0);
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("carbs").setValue(0);
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("fat").setValue(0);
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .child("protein").setValue(0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(getContext(), "Health Tracker Values Reset", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), GalleryMenu.class);
        startActivity(intent);
    }





}