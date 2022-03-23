package com.sailaminoak.computeruniversity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

public class LoadingDialog {
    Activity activity;
    AlertDialog alertDialog;
    boolean cancelable=true;
    String customText;
    public LoadingDialog(Activity activity){
        this.activity=activity;
    }
    public void startAnimationDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        View view=LayoutInflater.from(activity).inflate(R.layout.custom_loading_dialog,null);
        TextView textView=view.findViewById(R.id.textViewForLoadingDialog);
        textView.setText(customText);
        builder.setView(view);
        builder.setCancelable(cancelable);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void closingAlertDialog(){
        try{
            alertDialog.dismiss();
        }catch (Exception e)
        {
            //just so many close after null object
        }

    }
    public LoadingDialog(Activity activity,boolean cancelable,String customText){
        this.activity=activity;
        this.cancelable=cancelable;
        this.customText=customText;

    }
}
