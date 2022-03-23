package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Canteen extends AppCompatActivity {
    ImageButton backButton;
    String canteenNames="";
    GridView gridView;
    ArrayList<CanteenData> canteenList=new ArrayList<>();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canteen);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
            String[] gridViewData = {"Unknown 1", "Unknown 2", "Unknown 3", "Unknown 4"};
            gridView = findViewById(R.id.grid_view);
            canteenAdapter mainAdapter = new canteenAdapter(this, gridViewData, true);
            gridView.setAdapter(mainAdapter);
            backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DisplayToast("Retrieving Data : Please Wait");
                }
            });
            getCanteen();
        }
    }
    void DisplayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void getCanteen(){
        FirebaseDatabase.getInstance().getReference("Canteens").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String canteenName=ds.getKey();
                    canteenNames+=(canteenName+",");
                    CanteenData canteenData=ds.getValue(CanteenData.class);
                    canteenList.add(canteenData);
                }
                String[] canteens=canteenNames.split(",");
                canteenAdapter mainAdapter=new canteenAdapter(Canteen.this,canteens,true);
                gridView.setAdapter(mainAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        displayCanteen(canteenList.get(position));
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void displayCanteen(CanteenData canteenData){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
        View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_canteen_data,findViewById(R.id.bottomSheetContainer));
        TextView phoneNumber=bottomSheetView.findViewById(R.id.canteenPhoneNumber);
        TextView availableItems=bottomSheetView.findViewById(R.id.availableItems);
        ImageView canteenImageView=bottomSheetView.findViewById(R.id.canteenImage);

            byte[] b = Base64.decode(canteenData.getCanteenImage(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            canteenImageView.setImageBitmap(bitmap);


        phoneNumber.setText(canteenData.getPhoneNumber());
        availableItems.setText(canteenData.getAvailableItems());
        TextView canteenName=bottomSheetView.findViewById(R.id.canteenName);
        canteenName.setText(canteenData.getCanteenName());
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNow(phoneNumber.getText().toString().trim());
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();

    }
    void callNow(String phoneNumber){
        try{
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + phoneNumber.trim();
            i.setData(Uri.parse(p));
            startActivity(i);
        }catch (Exception e){
            DisplayToast("Invalid Phone Number");
        }
    }
}