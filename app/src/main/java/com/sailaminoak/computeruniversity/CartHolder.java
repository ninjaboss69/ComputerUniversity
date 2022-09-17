package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartHolder extends RecyclerView.ViewHolder {
    ImageView itemImageView;
    ImageButton itemCountUp,itemCountDown;
    TextView itemTotalPriceTextView,itemCountTextView,itemSizeTextView,itemNameTextView;
    LinearLayout deleteLinearLayout,wholeLinear;
    public CartHolder(@NonNull View itemView) {
        super(itemView);
        itemImageView=itemView.findViewById(R.id.itemImageView);
        itemCountDown=itemView.findViewById(R.id.itemCountDown);
        itemCountUp=itemView.findViewById(R.id.itemCountUp);
        itemTotalPriceTextView=itemView.findViewById(R.id.itemTotalPrice);
        itemCountTextView=itemView.findViewById(R.id.itemCountTextView);
        itemSizeTextView=itemView.findViewById(R.id.itemSize);
        itemNameTextView=itemView.findViewById(R.id.itemNameTextView);
        deleteLinearLayout=itemView.findViewById(R.id.deleteLinearLayout);
        wholeLinear=itemView.findViewById(R.id.wholeLinear);
    }
}
