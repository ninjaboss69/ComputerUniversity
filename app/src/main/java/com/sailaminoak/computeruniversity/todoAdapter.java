package com.sailaminoak.computeruniversity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoHolder> {
    Context context;
    SharedPreferences sharedPreferences;
    ArrayList<todoModel> models;
    RelativeLayout relativeLayout;
    public todoAdapter(Context c,ArrayList<todoModel> d,RelativeLayout relativeLayout){
        context=c;
        models=d;
        this.relativeLayout=relativeLayout;
    }
    @NonNull
    @Override
    public todoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_display_custom_layout,null);
        return new todoHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull todoHolder holder, int position) {
        String requiredString=(models.get(position).getTime()+"#"+models.get(position).getTitle()+"#"+models.get(position).getColor()+"#"+models.get(position).getDay()+"#"+models.get(position).getDate()+"#"+models.get(position).getMonth()+"#"+models.get(position).getAlarmRequestCode());
        holder.mainText.setText(models.get(position).getTitle());
        holder.subText.setText(models.get(position).getTime());
        holder.done.setText(models.get(position).getDone());
        holder.day.setText(models.get(position).getDay());
        holder.date.setText(models.get(position).getDate());
        holder.month.setText(models.get(position).getMonth());
        String code=models.get(position).getAlarmRequestCode();
       int alarmRequestCode=0;
        try{
            alarmRequestCode=Integer.parseInt(code);
        }catch (Exception e){
            alarmRequestCode=100001;
        }
        holder.alarmRequestCode.setText(code);
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v,position);
            }
        });

            holder.mainText.setTextColor(models.get(position).getColor());
            holder.mainText.setPaintFlags(holder.mainText.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        int finalAlarmRequestCode = alarmRequestCode;
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newPosition = holder.getAdapterPosition();
                try{
                    deleteTask(newPosition,requiredString);
                    cancelAlarm(finalAlarmRequestCode);
                }catch (Exception e){
                    Log.d("abc","cannot cancel alarm");
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public void showPopUpMenu(View view, int position) {
       // final Task task = taskList.get(position);
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.content_todo, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuDelete:
                    String requiredString=(models.get(position).getTime()+"#"+models.get(position).getTitle()+"#"+models.get(position).getColor()+"#"+models.get(position).getDay()+"#"+models.get(position).getDate()+"#"+models.get(position).getMonth()+"#"+models.get(position).getAlarmRequestCode());
                    AlertDialog.Builder completeAlertDialog = new AlertDialog.Builder(context);
                    completeAlertDialog.setTitle("Task").setMessage("Are you sure").
                            setPositiveButton("yes", (dialog, which) -> {

                                try{
                                    cancelAlarm(Integer.parseInt(models.get(position).getAlarmRequestCode()));
                                    deleteTask(position,requiredString);
                                }catch (Exception e){
                                    Log.d("abc","cannot cancel alarm");
                                    Log.d("abc",e.getMessage());
                                }


                            })
                            .setNegativeButton("no", (dialog, which) -> dialog.cancel()).show();
                    break;

            }
            return false;
        });
        popupMenu.show();
    }
    public void deleteTask(int newPosition,String requiredString){

        models.remove(newPosition);
        if(models.size()==0){
            relativeLayout.setVisibility(View.VISIBLE);
        }
        notifyItemRemoved(newPosition);
        notifyItemRangeChanged(newPosition, models.size());
        sharedPreferences =context.getSharedPreferences("Main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String subString=sharedPreferences.getString("TodoLists","");
        String[] array=subString.split(",");
        String newString="";
        for(int i=0;i<array.length;i++){
            Log.d("abc",requiredString+" is required string");
            Log.d("abc",array[i]+" is current string");
            if(array[i].contentEquals(requiredString)){

            }else{
                newString=newString+","+array[i];
            }
        }
        editor.putString("TodoLists",newString);
        editor.apply();
    }
    void cancelAlarm(int requestCode){
        if(requestCode==100001){
            //no alarm set
            Log.d("abc","no alarm");
            return;
        }
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmActivity=new Intent(context,NotificationReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,requestCode,alarmActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "Alarme Canceled", Toast.LENGTH_SHORT).show();
    }
}
