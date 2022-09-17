package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class CurrentCart extends AppCompatActivity implements View.OnClickListener {

    RecyclerView addToCartRecyclerView;
    public static Button orderNow;
    TextView totalValueTextView, customerAddressTextView;
    SharedPreferencesHolder helper;
    static int totalPrice = 0;
    ArrayList<CurrentCartData> models;
    public static boolean canBuy = false;
    public static ArrayList<Integer> listValuesOfItem = new ArrayList<>();
    DatabaseHelper databaseHelper;
    boolean alreadyAddAddress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_cart);
        initWidget();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        addToCartRecyclerView.setLayoutManager(linearLayoutManager);
        helper = new SharedPreferencesHolder(this);
        String[] Items = helper.unpackString(helper.getValueFromKey("currentCart"), ",");
        totalPrice=0;
        for (String item : Items) {
            if (item.length() == 0) continue;
            String[] Item = helper.unpackString(item, "#");
            CurrentCartData model = new CurrentCartData();
            model.setItemCount(Item[0]);
            model.setItemName(Item[1]);
            model.setItemImage(Item[2]);
            model.setItemTotalPrice(Item[3]);
            model.setItemSize(Item[4]);
            model.setItemID(Item[5]);
            totalPrice += (Integer.parseInt(Item[3]));
            models.add(model);
        }
        LoadAddressFromDB();
        totalValueTextView.setText(String.valueOf(totalPrice));
        CurrentCartAdapter adapter = new CurrentCartAdapter(models, this, totalValueTextView);
        addToCartRecyclerView.setAdapter(adapter);
        setOrderNowButton();
        orderNow.setOnClickListener(this);
        //displayToast("total price "+totalPrice);
    }

    private void initWidget() {
        databaseHelper=new DatabaseHelper(this);
        orderNow = findViewById(R.id.orderNowButton);
        totalValueTextView = findViewById(R.id.totalValuePriceTextViewInCart);
        customerAddressTextView = findViewById(R.id.customerAddress);
        models = new ArrayList<>();
        addToCartRecyclerView = findViewById(R.id.addToCartRecyclerView);
    }

    @Override
    public void onClick(View v) {
        orderNow();
    }

    private void orderNow() {
        if (!canBuy || models.size() == 0) {
            displayToast("add something to cart");
            return;
        }
        this.totalPrice=0;
        helper.deleteKey("currentCart");
        setOrderNowButton();
        setAdapter();
        displayToast("successfully order");
        UCSMShop.setCartData(this);

    }

    private void displayToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    void setOrderNowButton() {
        canBuy&=alreadyAddAddress;
        if (totalPrice > 0) {
            orderNow.setBackground(getResources().getDrawable(R.drawable.corner));
            canBuy = true;
        } else {
            orderNow.setBackground(getResources().getDrawable(R.drawable.notbuybutton));
            canBuy = false;
        }
    }

    void setAdapter() {
        totalValueTextView.setText("0");
        models = new ArrayList<>();
        CurrentCartAdapter adapter = new CurrentCartAdapter(models, this, totalValueTextView);
        addToCartRecyclerView.setAdapter(adapter);

    }

    void LoadAddressFromDB() {
        customerAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddressEditor();
            }
        });
        try{
            Cursor res = databaseHelper.getData("select * from AddressData");
            res.moveToLast();
            String address=res.getString(0)+" , "+res.getString(1);
            customerAddressTextView.setText(address);
            alreadyAddAddress=true;

        }catch (Exception e){
            customerAddressTextView.setText("Add Address!");
            customerAddressTextView.setTextColor(getResources().getColor(R.color.colorAccent));
            //customerAddressTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            //customerAddressTextView.setTextSize(22.0f);
            Log.d("abd","no address here");
        }

    }
    void openAddressEditor(){
        BottomSheetDialog dialog=new BottomSheetDialog(this);
        View bottomSheetView= LayoutInflater.from(this).inflate(R.layout.layout_customer_address_editor,findViewById(R.id.bottomSheetContainer));
        Button saveButton=bottomSheetView.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText address=bottomSheetView.findViewById(R.id.editTextAddress);
               EditText phoneNumber=bottomSheetView.findViewById(R.id.editTextPhoneNumber);
               String a=address.getText().toString().trim();
               String p=phoneNumber.getText().toString().trim();
               if(a.length()==0 || p.length()==0){
                   displayToast("data have to be filled");
                   dialog.dismiss();
                   return;
               }
               try{
                   databaseHelper.queryData("CREATE TABLE if not exists AddressData( address TEXT , phonenumber TEXT)");
                   databaseHelper.insertAddressData(a,p,"AddressData");
               }catch (Exception e){
                   displayToast("database error! contact to admin.");
               }
               dialog.dismiss();
               LoadAddressFromDB();

           }
       });
        dialog.setContentView(bottomSheetView);
        dialog.show();
    }

}