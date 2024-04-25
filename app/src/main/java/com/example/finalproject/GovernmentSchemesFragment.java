package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GovernmentSchemesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView schemesRV;
    private ArrayList<SchemesModel> schemesModelArrayList;
    private SchemesAdapter schemesAdapter;

    private String mParam1;
    private String mParam2;

    public GovernmentSchemesFragment() {
        // Required empty public constructor
    }

    public static GovernmentSchemesFragment newInstance(String param1, String param2) {
        GovernmentSchemesFragment fragment = new GovernmentSchemesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_govt_schemes, container, false);

        schemesRV = view.findViewById(R.id.govtSchemesRecyclerView);
        schemesModelArrayList = new ArrayList<>();
        schemesAdapter = new SchemesAdapter(getContext(), schemesModelArrayList);
        schemesRV.setAdapter(schemesAdapter);

        schemesRV.setLayoutManager(new LinearLayoutManager(getContext()));

        getSchemesInfo();

        return view;
    }
//    private void getSchemesInfo() {
//        String url = "https://3e38129a-da8d-4a9b-ad2e-f0e972e9dd38.mock.pstmn.io//3";
//
//        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("API Response", response.toString());
//                try {
//                    schemesModelArrayList.clear(); // Clear existing data
//                    // Since the response is a single JSONObject, we don't need to iterate
//                    String schemeName = response.getString("name");
//                    String year = response.getString("launchYear");
//                    String vision = response.getString("vision");
//                    String iconUrl = response.getString("img");
//                    SchemesModel scheme = new SchemesModel(schemeName, year, vision, iconUrl);
//                    schemesModelArrayList.add(scheme);
//                    schemesAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("API Error", "Error occurred: " + error.getMessage());
//            }
//        });
//
//        requestQueue.add(jsonObjectRequest);
//    }



//    private void getSchemesInfo() {
//        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
//
//        // Loop to make 10 API calls
//        for (int i = 0; i < 10; i++) {
//            // Generate a random number between 1 and 10
//            int randomNumber = new Random().nextInt(10) + 1; // Note: add import java.util.Random;
//            String url = "https://3e38129a-da8d-4a9b-ad2e-f0e972e9dd38.mock.pstmn.io//" + randomNumber;
//
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//                            Log.d("API Response", response.toString());
//                            try {
//                                // Process the response
//                                String schemeName = response.getString("name");
//                                String year = response.getString("launchYear");
//                                String vision = response.getString("vision");
//                                String iconUrl = response.getString("img");
//                                SchemesModel scheme = new SchemesModel(schemeName, year, vision, iconUrl);
//
//                                // Since we're making multiple calls, consider whether you should clear the list every time.
//                                // schemesModelArrayList.clear(); // Decide based on your requirement
//
//                                schemesModelArrayList.add(scheme);
//                                schemesAdapter.notifyDataSetChanged();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("API Error", "Error occurred: " + error.getMessage());
//                }
//            });
//
//            requestQueue.add(jsonObjectRequest);
//        }
//    }

    private void getSchemesInfo() {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        ArrayList<Integer> numbers = new ArrayList<>();

        // Populate the list with numbers 1 through 10
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        // Shuffle the list to randomize the order
        Collections.shuffle(numbers);

        // Iterate over the shuffled list to make API calls
        for (int i = 0; i < numbers.size(); i++) {
            int randomNumber = numbers.get(i); // This ensures each number is used exactly once
            String url = "https://3e38129a-da8d-4a9b-ad2e-f0e972e9dd38.mock.pstmn.io//" + randomNumber;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                    response -> {
                        Log.d("API Response", response.toString());
                        try {
                            // Since we're iterating through a shuffled list,
                            // each number from 1 to 10 is used exactly once in random order.
                            String schemeName = response.getString("name");
                            String year = response.getString("launchYear");
                            String vision = response.getString("vision");
                            String iconUrl = response.getString("img");
                            SchemesModel scheme = new SchemesModel(schemeName, year, vision, iconUrl);

                            schemesModelArrayList.add(scheme);
                            schemesAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }, error -> Log.e("API Error", "Error occurred: " + error.getMessage()));

            requestQueue.add(jsonObjectRequest);
        }
    }






}

