package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button callSignUp, login;
    ImageView logo;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signupBtn);
        login = findViewById(R.id.signinBtn);
        logo = findViewById(R.id.logoIV);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);

                Pair[] pairs = new Pair[5];
                pairs[0] = new Pair<View, String>(logo, "logo_image");
                pairs[1] = new Pair<View, String>(username, "usernameTransition");
                pairs[2] = new Pair<View, String>(password, "passwordTransition");
                pairs[3] = new Pair<View, String>(login, "btnLogInTransition");
                pairs[4] = new Pair<View, String>(callSignUp, "btnSignUpTransition");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
                startActivity(intent, options.toBundle());
            }
        });
    }

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public void loginUser(View view) {
        // Temporary bypass of Firebase authentication for testing purposes
        // Directly go to Dashboard activity regardless of input
        /*Intent intent = new Intent(Login.this, Dashboard.class);
        startActivity(intent);
        */

        // Original Firebase authentication code - Uncomment when ready to use
        if (!validateUsername() | !validatePassword()) {
            return;
        } else {
            isUser();
        }

    }

    private void isUser() {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    DataSnapshot userSnapshot = dataSnapshot.getChildren().iterator().next();
                    String passwordFromDB = userSnapshot.child("password").getValue(String.class);

                    if (passwordFromDB != null && passwordFromDB.equals(userEnteredPassword)) {
                        String nameFromDB = userSnapshot.child("name").getValue(String.class);
                        String usernameFromDB = userSnapshot.child("username").getValue(String.class);
                        String phoneNoFromDB = userSnapshot.child("phoneNo").getValue(String.class);
                        String emailFromDB = userSnapshot.child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);


                        startActivity(intent);
                    } else {
                        password.setError("Wrong password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
