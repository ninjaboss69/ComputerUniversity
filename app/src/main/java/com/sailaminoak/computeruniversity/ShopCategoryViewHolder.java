package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopCategoryViewHolder extends RecyclerView.ViewHolder {
    TextView categoryNameTextView;
    RecyclerView shopItemRecyclerView;
    public ShopCategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryNameTextView=itemView.findViewById(R.id.shop_category_title);
        shopItemRecyclerView=itemView.findViewById(R.id.shop_item_recyclerView);
    }
}
