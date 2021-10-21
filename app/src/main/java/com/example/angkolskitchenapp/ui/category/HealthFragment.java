package com.example.angkolskitchenapp.ui.category;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.angkolskitchenapp.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class HealthFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        int textSize = 75;

        View root = inflater.inflate(R.layout.fragment_health, container, false);

        AnimatedPieView mAnimatedPieView = root.findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(10, Color.parseColor("#FF5765"), "Calories")).drawText(true).textSize(textSize).canTouch(true)
                .addData(new SimplePieInfo(10, Color.parseColor("#FFDB15"), "Carbs")).drawText(true).textSize(textSize).canTouch(true)
                .addData(new SimplePieInfo(10, Color.parseColor("#8A6FDF"), "Fat")).drawText(true).textSize(textSize).canTouch(true)
                .addData(new SimplePieInfo(10, Color.parseColor("#A8E10C"), "Protein")).drawText(true).textSize(textSize).canTouch(true)
                .duration(2000);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

        return root;
    }

}