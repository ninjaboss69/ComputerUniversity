package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopCategoryAdapter extends RecyclerView.Adapter<ShopCategoryViewHolder> {
    ArrayList<shop_category_data> models;
    Context c;

    public ShopCategoryAdapter(ArrayList<shop_category_data> models, Context c) {
        this.models = models;
        this.c = c;
    }

    @NonNull
    @Override
    public ShopCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_category_custom_view,parent,false);
        return new ShopCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopCategoryViewHolder holder, int position) {
        shop_category_data model=models.get(position);
        holder.categoryNameTextView.setText(model.getCategoryName());
        shop_item_data model1=new shop_item_data("1","400 MMK","","-50%");
        shop_item_data model2=new shop_item_data("1","500 MMK","","");
        shop_item_data model3=new shop_item_data("1","600 MMK","","Out of Stock");
        shop_item_data model4=new shop_item_data("1","700 MMK","","Unavailable");
        ArrayList<shop_item_data> itemModels=new ArrayList<>();
        itemModels.add(model1);
        itemModels.add(model2);
        itemModels.add(model3);
        itemModels.add(model4);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false);
        holder.shopItemRecyclerView.setLayoutManager(layoutManager);
        ShopItemAdapter adapter=new ShopItemAdapter(itemModels,c);
        holder.shopItemRecyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
