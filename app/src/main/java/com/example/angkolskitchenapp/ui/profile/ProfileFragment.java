package com.example.angkolskitchenapp.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.angkolskitchenapp.GalleryMenu;
import com.example.angkolskitchenapp.LoginActivity;
import com.example.angkolskitchenapp.PlaceOrderActivity;
import com.example.angkolskitchenapp.R;
import com.example.angkolskitchenapp.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView profileImg;
    EditText name, email, number, address;
    Button update, logout;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profileImg = root.findViewById(R.id.profile_img);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        number = root.findViewById(R.id.profile_number);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update);
        logout = root.findViewById(R.id.logout);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel userModel = snapshot.getValue(UserModel.class);

                Glide.with(getContext()).load(userModel.getProfileImg()).into(profileImg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateUserProfile();
            }
        });

        logout.setOnClickListener(new  View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(getContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void updateUserProfile() {

        boolean nameEmpty = TextUtils.isEmpty(name.getText().toString());
        boolean emailEmpty = TextUtils.isEmpty(email.getText().toString());
        boolean phoneEmpty = TextUtils.isEmpty(number.getText().toString());
        boolean addressEmpty = TextUtils.isEmpty(address.getText().toString());

        if (!nameEmpty) {

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .child("name").setValue(name.getText().toString());
        }

        if (!emailEmpty) {

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .child("email").setValue(email.getText().toString());
        }

        if (!phoneEmpty) {

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .child("phoneNum").setValue(number.getText().toString());
        }

        if (!addressEmpty) {

            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                    .child("address").setValue(address.getText().toString());
        }


        Toast.makeText(getContext(), "Profile Updated", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), GalleryMenu.class);
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null) {
            Uri profileUri = data.getData();
            profileImg.setImageURI(profileUri);

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });

        }
    }
}