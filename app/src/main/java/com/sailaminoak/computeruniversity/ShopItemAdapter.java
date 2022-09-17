package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemViewHolder> {
    ArrayList<shop_item_data> models;
    Context c;

    public ShopItemAdapter(ArrayList<shop_item_data> models, Context c) {
        this.models = models;
        this.c = c;
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item_view,parent,false);
        return new ShopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        shop_item_data model=models.get(position);
        holder.shopItemPriceTextView.setText(model.getItemPrice());

        if(model.getItemExtraData().length()==0){
            holder.shop_extraText.setVisibility(View.GONE);
        }else{
            holder.shop_extraText.setText(model.getItemExtraData());
        }
        holder.shopItemImageView.setImageResource(R.drawable.ucsm_cup_example);
        holder.shopItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(new Intent(c,Shop_Detail_View.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
