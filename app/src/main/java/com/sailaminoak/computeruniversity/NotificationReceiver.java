package com.sailaminoak.computeruniversity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class NotificationReceiver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManagerCompat;
    SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("abc","started");
        String rawString=intent.getStringExtra("rawString");
            if(rawString!=null) {
                //Log.d("abc","do nothing");
                int uniqueId=intent.getIntExtra("id",1);
                Log.d("abc","unique id "+uniqueId);
                String data=intent.getStringExtra("data");
                Log.d("abc",data +" is  the data");
                T(context,rawString,uniqueId,data);
                Calendar calendar=Calendar.getInstance();
                long timeStamp=Long.parseLong(rawString);
                calendar.setTimeInMillis(timeStamp);
                createAnAlarm(context,calendar,10001,intent.getStringExtra("title"),intent.getStringExtra("description"));
                Log.d("abc","receiving on rawString not null");
               /*
                sharedPreferences=context.getSharedPreferences("Main",Context.MODE_PRIVATE);
                String subString=sharedPreferences.getString("TodoLists","");
                subString=subString+","+("101"+"#"+ "Mixing with Ingredients" +"#"+R.color.colorRed_900+"#"+"Tue"+"#"+"15"+"#"+"Jan"+"#"+"789");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("TodoLists",subString);
                editor.apply();
                notificationManagerCompat=NotificationManagerCompat.from(context);
                notificationManagerCompat.cancel(rawString,0);
                Log.d("abc","successfully put "+rawString+" to lists");
                Log.d("abc",rawString);

                */
            }else{
                Log.d("abc","receiving on rawString null");
                String title=intent.getStringExtra("title");
                String description=intent.getStringExtra("description");
                notificationManagerCompat=NotificationManagerCompat.from(context);
                sendOnChannelOne(context,title,description);
            }



    }
    private void sendOnChannelOne(Context context,String title,String description) {
        Intent activityIntent=new Intent(context,todolist.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,activityIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        //Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.assignment);
        Notification notification=new NotificationCompat.Builder(context,App.Channel1_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Reminder")
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory("Channel One")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title))
                .build();
        notificationManagerCompat.notify(String.valueOf(System.currentTimeMillis()), 0,notification);

    }
    void T(Context context,String tagofNoti,int uniqueId,String data){

        /*sharedPreferences=context.getSharedPreferences("Main",Context.MODE_PRIVATE);
        String subString=sharedPreferences.getString("TodoLists","");
        subString=subString+","+("101"+"#"+ "Mixing with Ingredients" +"#"+R.color.colorRed_900+"#"+"Tue"+"#"+"15"+"#"+"Jan"+"#"+"789");
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("TodoLists",subString);
        editor.apply();

         */
        DatabaseHelper helper=new DatabaseHelper(context);
        helper.queryData("CREATE TABLE if not exists AssignmentData( sentence TEXT )");
        helper.insertData(data,"AssignmentData");
        //notificationManagerCompat=NotificationManagerCompat.from(context);
        //notificationManagerCompat.cancel(rawString,0);
        Log.d("abc","successfully put  to lists");
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
        notificationManagerCompat.cancel(tagofNoti,uniqueId);

    }
    AlarmManager alarmManager;
    void createAnAlarm(Context context,Calendar calendar, int alarmRequestCode,String title,String description){
        try{
            alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            Intent alarmActivity=new Intent(context,NotificationReceiver.class);
            alarmActivity.putExtra("title",title);
            alarmActivity.putExtra("description",description);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(context,alarmRequestCode,alarmActivity,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);



        }catch (Exception e){
            Toast.makeText(context,"Cannot Set Alarm",Toast.LENGTH_SHORT).show();
        }
    }



}
