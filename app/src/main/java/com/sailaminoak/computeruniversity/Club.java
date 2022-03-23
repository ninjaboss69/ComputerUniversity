package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Club extends AppCompatActivity {
    ImageButton backButton;
    String clubNames="";
    GridView gridView;
    ArrayList<ClubData> clubData=new ArrayList<>();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
            String[] gridViewData = {"Tech Club", "Music Club", "Dance Club", "Football Club"};
            gridView = findViewById(R.id.grid_view);
            canteenAdapter mainAdapter = new canteenAdapter(this, gridViewData, false);
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
                    DisplayToast("Club Data are Loading : Please Wait");
                }
            });
            try {
                getClub();
            } catch (Exception e) {
                DisplayToast("Canteen are not available. Server Error");
            }
        }
    }
    void DisplayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void getClub(){
        FirebaseDatabase.getInstance().getReference("Clubs").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    String clubName=ds.getKey();
                    clubNames+=(clubName+",");
                    ClubData club=ds.getValue(ClubData.class);
                    clubData.add(club);
                }
                String[] clubs=clubNames.split(",");
                canteenAdapter mainAdapter=new canteenAdapter(Club.this,clubs,false);
                gridView.setAdapter(mainAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                       showClubData(clubData.get(position));
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void showClubData(ClubData clubData){
        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this,R.style.BottomSheetDialogTheme);
        View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.show_club_layout,findViewById(R.id.bottomSheetContainer));
        TextView clubName=bottomSheetView.findViewById(R.id.clubName);
        clubName.setText(clubData.getClubName());
        TextView clubEmail=bottomSheetView.findViewById(R.id.clubEmail);
        clubEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailNow(clubData.getClubEmail(),"","");
            }
        });
        clubEmail.setText(clubData.getClubEmail());
        TextView clubPhone=bottomSheetView.findViewById(R.id.clubPhoneNumber);
        clubPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNow(clubData.getClubPhoneNumber());
            }
        });

        clubPhone.setText(clubData.getClubPhoneNumber());
        TextView clubFees=bottomSheetView.findViewById(R.id.clubFees);
        clubFees.setText("1500 Kyats Monthly");
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
   private void callNow(String phoneNumber){
        try{
            Intent i = new Intent(Intent.ACTION_DIAL);
            String p = "tel:" + phoneNumber.trim();
            i.setData(Uri.parse(p));
            startActivity(i);
        }catch (Exception e){
            DisplayToast("Invalid Phone Number");
        }
    }
    private void emailNow(String recipient,String subject,String message){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT,new String[]{subject});
        intent.putExtra(Intent.EXTRA_TEXT,new String[]{message});
        try{
            startActivity(Intent.createChooser(intent,"Please Describe Details"));
        }catch (Exception e){
            DisplayToast(e.getMessage());
        }
    }
}