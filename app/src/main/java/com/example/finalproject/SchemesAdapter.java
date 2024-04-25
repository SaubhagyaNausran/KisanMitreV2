package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class SchemesAdapter extends RecyclerView.Adapter<SchemesAdapter.ViewHolder> {

    private Context context;
    private ArrayList<SchemesModel> schemesModelArrayList;

    public SchemesAdapter(Context context, ArrayList<SchemesModel> schemesModelArrayList) {
        this.context = context;
        this.schemesModelArrayList = schemesModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schemescardview, parent, false);
        return new ViewHolder(view);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        SchemesModel model = schemesModelArrayList.get(position);
//        holder.nameTV.setText(model.getName());
//        holder.launchYearTV.setText(model.getLaunchYear());
//        holder.visionTV.setText(model.getVision());
//        Picasso.get().load(model.getIcon()).into(holder.iconIV); // Assuming direct URL is used
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SchemesModel model = schemesModelArrayList.get(position);
        if (holder.nameTV != null) {
            holder.nameTV.setText(model.getName());
        } else {
            Log.e("SchemesAdapter", "nameTV is null");
        }
        if (holder.launchYearTV != null) {
            holder.launchYearTV.setText(model.getLaunchYear());
        } else {
            Log.e("SchemesAdapter", "launchYearTV is null");
        }
        if (holder.visionTV != null) {
            holder.visionTV.setText(model.getVision());
        } else {
            Log.e("SchemesAdapter", "visionTV is null");
        }
        if (holder.iconIV != null) {
            Picasso.get().load(model.getIcon()).into(holder.iconIV);
        } else {
            Log.e("SchemesAdapter", "iconIV is null");
        }
    }


    @Override
    public int getItemCount() {
        return schemesModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV, launchYearTV, visionTV;
        private ImageView iconIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.schemeNameTV);
            launchYearTV = itemView.findViewById(R.id.launchYearTV);
            visionTV = itemView.findViewById(R.id.visionTV);
            iconIV = itemView.findViewById(R.id.schemeIV);
        }
    }
}
