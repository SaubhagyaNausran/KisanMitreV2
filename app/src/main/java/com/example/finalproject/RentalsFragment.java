package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class RentalsFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Member variables to hold the values passed in the bundle
    private String mParam1;
    private String mParam2;


    private CardView cardViewMumbai, cardViewHyderabad, cardViewChennai, cardViewBangalore;

    public RentalsFragment() {
        // Required empty public constructor
    }

    public static RentalsFragment newInstance(String param1, String param2) {
        RentalsFragment fragment = new RentalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rentals, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize CardViews
        cardViewMumbai = view.findViewById(R.id.cv_img_mumbai);
        cardViewHyderabad = view.findViewById(R.id.cv_img_hyderabad);
        cardViewChennai = view.findViewById(R.id.cv_img_chennai);
        cardViewBangalore = view.findViewById(R.id.cv_img_banglore);

        // Set click listeners for each CardView
        cardViewMumbai.setOnClickListener(v -> openRentMachineActivity("Mumbai"));
        cardViewHyderabad.setOnClickListener(v -> openRentMachineActivity("Hyderabad"));
        cardViewChennai.setOnClickListener(v -> openRentMachineActivity("Chennai"));
        cardViewBangalore.setOnClickListener(v -> openRentMachineActivity("Bangalore"));
    }

    private void openRentMachineActivity(String city) {
        Intent intent = new Intent(getActivity(), RentMachineActivity.class);
        intent.putExtra("CITY_NAME", city);
        startActivity(intent);
    }
}
