package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop_Detail_View extends AppCompatActivity implements View.OnClickListener {

    TextView shop_item_description,shop_item_size,shop_item_totalCost,shop_details_item_count;
    ImageButton itemCountUp,itemCountDown,sizeUp,sizeDown;
    int itemPrice=5;
    int itemCount=0;
    int total=0;
    String itemSize="medium",itemName="UCSM Cups";
    Button addToCart;
    Boolean canAddToCart=false;
    SharedPreferencesHolder sharedPreferencesHolder;
    Map<String,Integer> map=new HashMap<>();
    int currentIndex=0;
    String[] keyArray;
    int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__detail__view);
        sharedPreferencesHolder=new SharedPreferencesHolder(this);
        shop_item_description=findViewById(R.id.shop_tem_description);
        shop_item_size=findViewById(R.id.sizeText);
        shop_item_totalCost=findViewById(R.id.totalCostofItems);
        itemCountDown=findViewById(R.id.itemCountDown);
        itemCountUp=findViewById(R.id.itemCountUp);
        sizeDown=findViewById(R.id.sizeDown);
        sizeUp=findViewById(R.id.sizeUp);
        shop_details_item_count=findViewById(R.id.shop_detail_item_count);
        addToCart=findViewById(R.id.addToCart);
        map.put("Small",150);
        map.put("Medium",200);
        map.put("Large",500);
        map.put("XXL",1500);
        keyArray=new String[map.size()];
        keyArray[k++]="Small";
        keyArray[k++]="XXL";
        keyArray[k++]="Medium";
        keyArray[k++]="Large";
        if(keyArray.length==0)return;
        currentIndex%=keyArray.length;
        shop_item_size.setText(keyArray[currentIndex]);
        itemPrice=map.get(keyArray[currentIndex]);
        itemSize=keyArray[currentIndex];
        ArrayList<SlideModel> models=new ArrayList<>();
        models.add(new SlideModel(R.drawable.ucsmbackground,"small", ScaleTypes.CENTER_CROP));
        models.add(new SlideModel(R.drawable.ucsm_cup_example,"medium", ScaleTypes.CENTER_CROP));
        models.add(new SlideModel("https://bit.ly/3fLJf72","large", ScaleTypes.CENTER_CROP));
        ImageSlider imageSlider=findViewById(R.id.image_slider);
        imageSlider.setImageList(models);
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                imageSlider.stopSliding();
            }
        });
        itemCountDown.setOnClickListener(this);
        itemCountUp.setOnClickListener(this);
        sizeUp.setOnClickListener(this);
        sizeDown.setOnClickListener(this);
        addToCart.setOnClickListener(this);

    }
    void updateTextView(int price,int count,String size){
        currentIndex%=keyArray.length;
        shop_item_size.setText(size);
        price=map.getOrDefault(size,0);
        shop_details_item_count.setText(count+"*"+itemName);
        total=price*count;
        shop_item_totalCost.setText(String.valueOf(total));
        setColorToAddToCartButton(count);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.itemCountDown:
                if(itemCount<=0){
                    break;
                }
                itemCount--;
                updateTextView(itemPrice,itemCount,itemSize);
                break;
            case R.id.itemCountUp:
                itemCount++;
                updateTextView(itemPrice,itemCount,itemSize);
                break;
            case R.id.addToCart:
                if(!canAddToCart){
                    break;
                }
                addToCart();
                break;
            case R.id.sizeDown:
                currentIndex--;
                if(currentIndex<0){
                    currentIndex=keyArray.length-1;
                }
                itemSize=keyArray[currentIndex];
                updateTextView(itemPrice,itemCount,itemSize);
                break;
            case R.id.sizeUp:
                currentIndex++;
                currentIndex%=keyArray.length;
                itemSize=keyArray[currentIndex];
                updateTextView(itemPrice,itemCount,itemSize);
                break;
            default:
                break;
        }
    }

    private void addToCart() {
        boolean successToAddingCart=false;
        try{
            String formattedCartData=(itemCount+"#"+itemName+"#"+" "+"#"+total+"#"+itemSize+"#"+System.currentTimeMillis());
            sharedPreferencesHolder.addStringToKey("currentCart",formattedCartData,",");
            successToAddingCart=true;
            displayToast(sharedPreferencesHolder.getValueFromKey("currentCart"));
            UCSMShop.setCartData(this);
        }catch (Exception e){
            displayToast("Something wrong when adding cart. Resetting all cart data...");
            sharedPreferencesHolder.deleteKey("currentCart");
        }

       if(successToAddingCart) {
           itemCount=0;
           updateTextView(itemPrice,itemCount,itemSize);
       }
    }
    private void setColorToAddToCartButton(int itemCount){
        if(itemCount>0){
            canAddToCart=true;
            addToCart.setBackground(getDrawable(R.drawable.corner));
        }else{
            canAddToCart=false;
            addToCart.setBackground(getDrawable(R.drawable.notbuybutton));
        }
    }
    private void displayToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}