package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopItemViewHolder extends RecyclerView.ViewHolder {
    ImageView shopItemImageView;
    TextView shopItemPriceTextView,shop_extraText;
    public ShopItemViewHolder(@NonNull View itemView) {
        super(itemView);
        shopItemImageView=itemView.findViewById(R.id.shop_item_imageView);
        shopItemPriceTextView=itemView.findViewById(R.id.shop_item_price);
        shop_extraText=itemView.findViewById(R.id.shop_extraText);
    }
}
