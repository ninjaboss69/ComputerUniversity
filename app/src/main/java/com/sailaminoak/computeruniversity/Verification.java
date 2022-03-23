package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class Verification extends AppCompatActivity {
    private static final String TAG ="bug" ;
    EditText mkpt,password;
    TextView forgotPassword;
    CircularProgressButton loginButton;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        String mainString=sharedPreferences.getString("userName","");
        mkpt=findViewById(R.id.editTextMKPT);
        password=findViewById(R.id.editTextPassword);
        loginButton=findViewById(R.id.cirLoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                      verify();
            }
        });
        forgotPassword=findViewById(R.id.forgorPasswordTextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailNow("macabrepanpapakhin@gmail.com","Forgetting Password","");
            }
        });

    }
    private void displayToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void verify(){
        String mkptString =mkpt.getText().toString().trim();
        String passwordString=password.getText().toString().trim();
        if(mkptString.length()!=0 && passwordString.length()!=0){
            FirebaseDatabase.getInstance().getReference("Verification").child(mkptString).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            registerData data=snapshot.getValue(registerData.class);
                            if(passwordString.contentEquals(data.getPassword())){
                                displayToast("Verify Success, Please Reopen the App");

                                String userName=data.getName();
                                try{
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("auth",true);
                                    editor.putString("userName",userName);
                                    editor.apply();
                                }catch (Exception e){
                                    displayToast("Error Login");
                                }


                            }else{
                                displayToast("Wrong Password");
                            }


                        }else{
                            displayToast("Data Not Found");
                        }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else{
            displayToast("Fields are required");
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
            displayToast(e.getMessage());
        }
    }


}