package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PendingOrderViewHolder extends RecyclerView.ViewHolder {
    TextView itemPrice,itemName,shippingAddress,pendingOrNot;
    ImageView itemImage,pendingImage;
    public PendingOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        itemPrice=itemView.findViewById(R.id.itemPrice);
        itemName=itemView.findViewById(R.id.itemName);
        shippingAddress=itemView.findViewById(R.id.shippingAddress);
        itemImage=itemView.findViewById(R.id.itemImage);
        pendingOrNot=itemView.findViewById(R.id.pendingTextView);
        pendingImage=itemView.findViewById(R.id.pendingImage);
    }
}
