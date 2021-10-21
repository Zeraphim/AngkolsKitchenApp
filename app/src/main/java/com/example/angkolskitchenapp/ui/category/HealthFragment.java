package com.example.angkolskitchenapp.ui.category;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.angkolskitchenapp.GalleryMenu;
import com.example.angkolskitchenapp.R;
import com.example.angkolskitchenapp.model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class HealthFragment extends Fragment {

    TextView calories, carbs, protein, fat;

    FirebaseAuth auth;
    FirebaseDatabase database;


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

        // TextView Values

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel userModel = snapshot.getValue(UserModel.class);

                calories.setText(String.valueOf(userModel.getCalories()));
                carbs.setText(String.valueOf(userModel.getCarbs()));
                protein.setText(String.valueOf(userModel.getProtein()));
                fat.setText(String.valueOf(userModel.getFat()));

                // Pie Chart

                AnimatedPieView mAnimatedPieView = root.findViewById(R.id.animatedPieView);
                AnimatedPieViewConfig config = new AnimatedPieViewConfig();
                config.startAngle(-90)// Starting angle offset
                        .addData(new SimplePieInfo(userModel.getCalories(), Color.parseColor("#FF5765"), "Calories")).drawText(true).textSize(textSize).canTouch(true)
                        .addData(new SimplePieInfo(userModel.getCarbs(), Color.parseColor("#FFDB15"), "Carbs")).drawText(true).textSize(textSize).canTouch(true)
                        .addData(new SimplePieInfo(userModel.getProtein(), Color.parseColor("#8A6FDF"), "Fat")).drawText(true).textSize(textSize).canTouch(true)
                        .addData(new SimplePieInfo(userModel.getFat(), Color.parseColor("#A8E10C"), "Protein")).drawText(true).textSize(textSize).canTouch(true)
                        .duration(2000);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
                mAnimatedPieView.applyConfig(config);
                mAnimatedPieView.start();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }

}