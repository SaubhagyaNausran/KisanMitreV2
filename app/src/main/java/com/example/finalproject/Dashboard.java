package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize the BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        // Set HomeFragment as the default fragment
        replaceFragment(new HomeFragment());



        // Set the item selected listener for the BottomNavigationView
        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    return true;
                case R.id.cropPrediction:
                    replaceFragment(new CropPredictionFragment());
                    return true;
                case R.id.weather:
                    replaceFragment(new WeatherFragment());
                    return true;
                case R.id.mandi:
                    replaceFragment(new MandiFragment());
                    return true;
                case R.id.more:
                    replaceFragment(new DiseasePredictionFragment());
                    return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_profile) {
            // Create an Intent to start the ProfileActivity
            //Intent intent = new Intent(this, UserProfile.class);
            //startActivity(intent);
            launchUserProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void launchUserProfile() {
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        Intent userProfileIntent = new Intent(this, UserProfile.class);
        userProfileIntent.putExtra("username", username); // Pass username to UserProfile
        startActivity(userProfileIntent);
    }



    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
