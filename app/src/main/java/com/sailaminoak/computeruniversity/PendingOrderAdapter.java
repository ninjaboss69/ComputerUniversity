package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PendingOrderAdapter extends RecyclerView.Adapter<PendingOrderViewHolder> {
    Context context;
    ArrayList<PendingOrderData> models;

    public PendingOrderAdapter(Context context, ArrayList<PendingOrderData> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public PendingOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_pending_order,parent,false);
        return new PendingOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrderViewHolder holder, int position) {
        PendingOrderData model=models.get(position);
        holder.itemImage.setImageResource(R.drawable.ucsm_cup_example);
        if(model.getPendingOrNot().equals("Yes".toLowerCase())){
            holder.pendingImage.setImageResource(R.drawable.ic_baseline_pending_actions_24);
            holder.pendingOrNot.setText("Pending");
        }else{
            holder.pendingImage.setImageResource(R.drawable.ic_baseline_check_24);
            holder.pendingOrNot.setText("Delivered");
        }
        holder.itemName.setText(model.getItemName()+" * "+model.getItemCount());
        holder.shippingAddress.setText(model.getShippingAddress());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
