package com.sailaminoak.computeruniversity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
public class CoverDialog {
    Activity activity;
    AlertDialog alertDialog;
    boolean cancelable=true;
    public CoverDialog(){

    }
    public void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        View view= LayoutInflater.from(activity).inflate(R.layout.closing_layout,null);
        Button loginTextView=view.findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
                Intent intent=new Intent(activity,Verification.class);
                activity.startActivity(intent);
            }
        });
        Button goBackButton=view.findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
                activity.finish();

            }
        });
        builder.setView(view);
        builder.setCancelable(cancelable);
        alertDialog=builder.create();
        alertDialog.show();
    }
    public void dismissDialog(){
        alertDialog.dismiss();
    }
    public CoverDialog(Activity activity,boolean cancelable){
        this.activity=activity;
        this.cancelable=cancelable;
    }
}

