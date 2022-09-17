package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UCSMShop extends AppCompatActivity {
    DrawerLayout shop_drawer_layout;
    NavigationView shop_navigation_view;
    Toolbar shop_toolbar;
    ImageView menuLists;
    RecyclerView categoryRecyclerView;
    ArrayList<shop_category_data> models;
    static TextView itemCountInCart;
    static ImageButton cartButton;
    SharedPreferencesHolder helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_c_s_m_shop);
        shop_toolbar=findViewById(R.id.shop_toolbar);
        menuLists=findViewById(R.id.menuList);
        shop_drawer_layout=findViewById(R.id.shop_drawer_layout);
        shop_navigation_view=findViewById(R.id.shop_navigation_view);
        categoryRecyclerView=findViewById(R.id.categoryRecyclerView);
        cartButton=findViewById(R.id.toolbar_add_to_cart);
        itemCountInCart=findViewById(R.id.itemCountInCart);
        models=new ArrayList<>();
        helper=new SharedPreferencesHolder(this);
        setCartData(this);
        menuLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shop_drawer_layout.isDrawerOpen(GravityCompat.START)){
                    shop_drawer_layout.closeDrawer(Gravity.LEFT);
                }else{
                    shop_drawer_layout.openDrawer(Gravity.LEFT);
                }
            }
        });
        shop_navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_order:
                        //startActivity(new Intent(getApplicationContext(),CurrentCart.class));
                        startActivity(new Intent(getApplicationContext(),ShopeOrder.class));
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
        shop_category_data model1=new shop_category_data("","UCSM Cups");
        shop_category_data model2=new shop_category_data("","Flags");
        shop_category_data model3=new shop_category_data("","Sticker");
        shop_category_data model4=new shop_category_data("","Clothes");
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        ShopCategoryAdapter adapter=new ShopCategoryAdapter(models,this);
        categoryRecyclerView.setLayoutManager(linearLayoutManager);
        categoryRecyclerView.setAdapter(adapter);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CurrentCart.class));
            }
        });

    }
   static void setCartData(Context c){
       SharedPreferencesHolder helper=new SharedPreferencesHolder(c);
        if(itemCountInCart==null || cartButton==null){
            helper.deleteKey("currentCart");

            return;
        }

        String[] itemsInCart=helper.unpackString(helper.getValueFromKey("currentCart"),",");
        int itemTotal=0;
        for(String i:itemsInCart){
            if(i.length()>0){
                itemTotal++;
            }
        }
        if(itemTotal>=1){
            itemCountInCart.setText(String.valueOf(itemTotal));
            itemCountInCart.setVisibility(View.VISIBLE);
            cartButton.setVisibility(View.VISIBLE);
        }else{
            itemCountInCart.setVisibility(View.GONE);
            cartButton.setVisibility(View.GONE);
        }
    }
    void displayToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}