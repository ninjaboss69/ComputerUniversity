package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AboutSoftware extends AppCompatActivity {
    Button reportButton;
    TextView dT,v,rD,p,rM,aB,sRN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_software);
        dT=findViewById(R.id.developerTeam);
        v=findViewById(R.id.version);
        rD=findViewById(R.id.releaseDate);
        p=findViewById(R.id.platform);
        rM=findViewById(R.id.remark);
        aB=findViewById(R.id.about);
        reportButton=findViewById(R.id.reportBugButton);
        sRN=findViewById(R.id.softwareReleaseNote);
        sRN.setText(R.string.bugFixText);
        sRN.setMovementMethod(LinkMovementMethod.getInstance());
        FirebaseDatabase.getInstance().getReference("Software").child("AboutSoftware").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                softwareDetails data=snapshot.getValue(softwareDetails.class);
                setUpData(data.getDeveloperTeam(),data.getVersion(),data.getPlatform(),data.getReleaseDate(),data.getRemark(),data.getAbout());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportBug("macabrepanpapakhin@gmail.com","UCSM Software Bug Reporting","");
            }
        });
    }
    private void reportBug(String recipient,String subject,String message){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT,new String[]{subject});
        intent.putExtra(Intent.EXTRA_TEXT,new String[]{message});
        try{
            startActivity(Intent.createChooser(intent,"Choose Preferred Email Software"));
        }catch (Exception e){
            displayToast(e.getMessage());
        }
    }
    private void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
    private void setUpData(String developerTeam,String version,String platform,String releaseDate,String remark,String about){
        dT.setText(developerTeam);
        v.setText(version);
        p.setText(platform);
        rD.setText(releaseDate);
        rM.setText(remark);
        aB.setText(about);
        aB.setMovementMethod(LinkMovementMethod.getInstance());
        dT.setMovementMethod(LinkMovementMethod.getInstance());
        v.setMovementMethod(LinkMovementMethod.getInstance());
        p.setMovementMethod(LinkMovementMethod.getInstance());
        rD.setMovementMethod(LinkMovementMethod.getInstance());
        rM.setMovementMethod(LinkMovementMethod.getInstance());

    }

}