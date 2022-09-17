package com.sailaminoak.computeruniversity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class alarmListAdapter extends RecyclerView.Adapter<alarmListHolder> {

    Context context;
    ArrayList<todoAlarmModel> models;
    communicator Communicator;
    public alarmListAdapter(Context context, ArrayList<todoAlarmModel> models){
        this.context=context;
       this.models=models;
    }

    @NonNull
    @Override
    public alarmListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater=LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_alarm_list,parent,false);
        return new alarmListHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull alarmListHolder holder, int position) {
        todoAlarmModel model=models.get(position);
            holder.alarmDay.setText(model.alarmDay);
            holder.alarmDescription.setText(model.alarmDescription);
            holder.alarmDate.setText(model.getAlarmDate());
            holder.alarmMonth.setText(model.getAlarmMonth());
            holder.alarmTime.setText(model.getAlarmTime());
            holder.alarmTag.setText(model.getAlarmTag());
            holder.alarmMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showDeleteAndUpdate();
                    showPopUpMenu(v,model.getAlarmRequestCode());

                }
            });

    }

    private void showDeleteAndUpdate() {
        Log.d("abc","will show update and delete view");
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    void showPopUpMenu(View view,String alarmID){
        PopupMenu popupMenu=new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.alarm_list_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuCancelAlarm:
                        cancelAlarmWithId(alarmID);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    boolean created=false;
    void cancelAlarmWithId(String alarmID){
      DatabaseHelper databaseHelper=new DatabaseHelper(context);
      databaseHelper.queryData("delete from AlarmData where id = "+alarmID);
     if(created) {
         Communicator.passData(1);
         created=false;
     }
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmActivity=new Intent(context,NotificationReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,Integer.parseInt(alarmID),alarmActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "Canceled Alarm!", Toast.LENGTH_SHORT).show();
    }
    public void passVal(communicator Comm) {
        this.Communicator=Comm;
        created=true;
        Log.d("abd","communicator created");

    }


}
