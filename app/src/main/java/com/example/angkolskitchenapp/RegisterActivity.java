package com.example.angkolskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.angkolskitchenapp.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager mNotifyManager;

    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    TextView tvLoginHere;
    Button btnRegister;

    FirebaseAuth mAuth;

    FirebaseAuth auth;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPass);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        createNotificationChannel();
        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser(){
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){

                        String userName = "Angkol's Kitchen App";
                        String userEmail = etRegEmail.getText().toString();
                        String userPassword = etRegPassword.getText().toString();
                        String profileImg = "https://firebasestorage.googleapis.com/v0/b/angkolskitchenapp.appspot.com/o/profile.png?alt=media&token=5ee2dc2e-8d0d-45a9-bd9b-0c3740132eee";

                        int calories = 0;
                        int carbs = 0;
                        int fat = 0;
                        int protein = 0;

                        UserModel userModel = new UserModel(userName, userEmail, userPassword, profileImg, calories, carbs, fat, protein);
                        String id = task.getResult().getUser().getUid();
                        database.getReference().child("Users").child(id).setValue(userModel);

                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        sendNotification();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Welcome to the Angkol Community!")
                .setContentText("Check your account for your Angkol promos!")
                .setSmallIcon(R.drawable.ic_promo);
        return notifyBuilder;
    }

}