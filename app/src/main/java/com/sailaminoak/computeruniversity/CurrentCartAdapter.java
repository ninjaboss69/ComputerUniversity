package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CurrentCartAdapter extends RecyclerView.Adapter<CartHolder> {

    ArrayList<CurrentCartData> models;
    Context context;
    SharedPreferencesHolder helper;
    TextView totalValue;
    Boolean canBuy=false;
    int realTotal;
    public CurrentCartAdapter(ArrayList<CurrentCartData> models, Context context,TextView textView) {
        this.models = models;
        this.context = context;
        helper=new SharedPreferencesHolder(context);
        this.totalValue=textView;
        realTotal=CurrentCart.totalPrice;

    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_shop_item_in_cart,parent,false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        CurrentCartData model=models.get(position);
        String requiredString=model.getItemCount()+"#"+model.getItemName()+"#"+model.getItemImage()+"#"+model.getItemTotalPrice()+"#"+model.getItemSize()+"#"+model.getItemID();
        holder.itemNameTextView.setText(model.getItemName());
        holder.itemCountTextView.setText(model.getItemCount());
        holder.itemSizeTextView.setText(model.getItemSize());
        holder.itemTotalPriceTextView.setText(model.getItemTotalPrice());
        final int[] itemCount = {Integer.parseInt(model.getItemCount())};
        int itemPrice=Integer.parseInt(model.getItemTotalPrice())/ itemCount[0];
        Log.d("abc",helper.getValueFromKey("currentCart"));
        holder.deleteLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realTotal-=Integer.parseInt(model.getItemTotalPrice());
                totalValue.setText(String.valueOf(realTotal));
                models.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,models.size());
                helper.removeStringFromKey("currentCart",requiredString,",");
                setUpOrderNowButton();
                UCSMShop.setCartData(context);
            }
        });
        holder.itemCountUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                itemCount[0]++;
                realTotal-=Integer.parseInt(holder.itemTotalPriceTextView.getText().toString().trim());
                updateTextView(itemCount[0],itemPrice,holder.itemTotalPriceTextView,holder.itemCountTextView,model);
            }
        });
        holder.itemCountDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemCount[0] <=1){
                    return;
                }
                itemCount[0]--;
                realTotal-=Integer.parseInt(holder.itemTotalPriceTextView.getText().toString().trim());
                updateTextView(itemCount[0],itemPrice,holder.itemTotalPriceTextView,holder.itemCountTextView,model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    void updateTextView(int count, int price, TextView itemTotal, TextView itemCountTextView,CurrentCartData model){
        int totalPrice=count*price;
        realTotal+=totalPrice;
        totalValue.setText(String.valueOf(realTotal));
        itemTotal.setText(String.valueOf(totalPrice));
        model.setItemTotalPrice(String.valueOf(totalPrice));
        model.setItemCount(String.valueOf(count));
        itemCountTextView.setText(String.valueOf(count));
    }
 void setUpOrderNowButton(){
     if(realTotal>0){
         CurrentCart.orderNow.setBackground(context.getResources().getDrawable(R.drawable.corner));
         CurrentCart.canBuy=true;
     }else{
         CurrentCart.orderNow.setBackground(context.getResources().getDrawable(R.drawable.notbuybutton));
         CurrentCart.canBuy=false;
     }
 }

}
