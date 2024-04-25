package com.example.finalproject;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RentMachineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RentMachineAdapter mAdapter;
    private List<RentMachineModel> machineList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_machine);

        mRecyclerView = findViewById(R.id.rentMachineRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        machineList = new ArrayList<>();
        mAdapter = new RentMachineAdapter(this, machineList);
        mRecyclerView.setAdapter(mAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("machines");

        String selectedCity = "YourSelectedCity"; // Replace this with the actual selected city

        databaseReference.orderByChild("city").equalTo(selectedCity).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                machineList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RentMachineModel machine = postSnapshot.getValue(RentMachineModel.class);
                    machineList.add(machine);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RentMachineActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
