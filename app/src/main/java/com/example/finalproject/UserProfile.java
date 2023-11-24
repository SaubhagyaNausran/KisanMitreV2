package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextInputLayout password,email,phoneNo,fullName;
    TextView fullNameLabel,usernameLabel;
    String user_username , user_name , user_email , user_phoneNo , user_password;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference= FirebaseDatabase.getInstance().getReference("users");


        fullName = findViewById(R.id.nameET);
        email = findViewById(R.id.emailET);
        phoneNo = findViewById(R.id.phoneNoET);
        password = findViewById(R.id.passwordET);
        fullNameLabel = findViewById(R.id.nameTV);
        usernameLabel = findViewById(R.id.usernameTV);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        user_username = intent.getStringExtra("username");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("username").equalTo(user_username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Assuming each username is unique and there's only one child
                    DataSnapshot userDataSnapshot = dataSnapshot.getChildren().iterator().next();

                    // Update your user details here
                    user_name = userDataSnapshot.child("name").getValue(String.class);
                    user_email = userDataSnapshot.child("email").getValue(String.class);
                    user_phoneNo = userDataSnapshot.child("phoneNo").getValue(String.class);
                    user_password = userDataSnapshot.child("password").getValue(String.class);

                    // Set the data to the views
                    fullNameLabel.setText(user_name);
                    usernameLabel.setText(user_username);
                    fullName.getEditText().setText(user_name);
                    email.getEditText().setText(user_email);
                    phoneNo.getEditText().setText(user_phoneNo);
                    password.getEditText().setText(user_password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }


//    private void showAllUserData() {
//
//        Intent intent = getIntent();
//        user_username = intent.getStringExtra("username");
//        user_name = intent.getStringExtra("name");
//        user_email = intent.getStringExtra("email");
//        user_phoneNo = intent.getStringExtra("phoneNo");
//        user_password = intent.getStringExtra("password");
//
//        fullNameLabel.setText(user_name);
//        usernameLabel.setText(user_username);
//        fullName.getEditText().setText(user_name);
//        email.getEditText().setText(user_email);
//        phoneNo.getEditText().setText(user_phoneNo);
//        password.getEditText().setText(user_password);
//    }

    public void update(View view){

        if(isNameChanged() || isPasswordChanged()){
            Toast.makeText(this,"data has been updated",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordChanged(){
        if(!user_password.equals(password.getEditText().getText().toString()))
        {
            reference.child(user_phoneNo).child("password").setValue(password.getEditText().getText().toString());
            user_password=password.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }
    private boolean isNameChanged(){
        if(!user_name.equals(fullName.getEditText().getText().toString())){
            reference.child(user_phoneNo).child("name").setValue(fullName.getEditText().getText().toString());
            user_name=fullName.getEditText().getText().toString();
            return true;
        }else{
            return false;
        }
    }
}