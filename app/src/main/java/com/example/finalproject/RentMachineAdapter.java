package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RentMachineAdapter extends RecyclerView.Adapter<RentMachineAdapter.ViewHolder> {

    private Context context;
    private List<RentMachineModel> machineList;

    public RentMachineAdapter(Context context, List<RentMachineModel> machineList) {
        this.context = context;
        this.machineList = machineList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rentalcardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RentMachineModel machine = machineList.get(position);

        holder.toolNameTV.setText(machine.getToolName());
        holder.manufacturingYearTV.setText(machine.getManufacturingYear());
        holder.priceTV.setText(machine.getPrice());
        holder.phoneTV.setText(machine.getPhone());
        holder.ownerNameTV.setText(machine.getOwnerName());
        holder.addressTV.setText(machine.getAddress());
    }

    @Override
    public int getItemCount() {
        return machineList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView toolNameTV, manufacturingYearTV, priceTV, phoneTV, ownerNameTV, addressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            toolNameTV = itemView.findViewById(R.id.toolNameTV);
            manufacturingYearTV = itemView.findViewById(R.id.manufacturingYearTV);
            priceTV = itemView.findViewById(R.id.priceTV);
            phoneTV = itemView.findViewById(R.id.phoneTV);
            ownerNameTV = itemView.findViewById(R.id.ownerNameTV);
            addressTV = itemView.findViewById(R.id.addressTV);
        }
    }
}

