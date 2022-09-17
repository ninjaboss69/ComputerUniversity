package com.sailaminoak.computeruniversity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.applandeo.materialcalendarview.utils.DateUtils;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    String notiTag="";
    NotificationManagerCompat not;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notiTag=String.valueOf(System.currentTimeMillis());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Assignment_From_Cloud");
        builder.setContentTitle(remoteMessage.getNotification().getTitle());
        builder.setContentText(remoteMessage.getNotification().getBody());
       try{
           String rawString=remoteMessage.getData().get("text");
           if(!rawString.contains("4SE")){
               Log.d("abc","Other Class Alerts. Returning");
               return;
           }
       }
       catch (Exception e){
           Log.d("abc","error formatting"+remoteMessage.getData().toString());
           return;

       }
        Log.d("abc","hello");
       Calendar calendar=Calendar.getInstance();
       calendar.add(Calendar.SECOND,30);
      // calendar.add(Calendar.DAY_OF_MONTH,1);
        String reminderTime=String.valueOf(calendar.getTimeInMillis());
        Log.d("abc","getdata");
        Log.d("abc",remoteMessage.getData()+"");
        Log.d("abc",remoteMessage.getNotification().getTitle());
        Log.d("abc",remoteMessage.getNotification().getBody());
        //Log.d("abc",remoteMessage.getNotification());
        sendOnChannelOne(getApplicationContext(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),reminderTime);




    }
    private void sendOnChannelOne(Context context,String title,String description,String reminderTime) {
        String tageOfNoti=reminderTime;
        int tageOfNotiInt=(int)System.currentTimeMillis()/100;
        Intent addToListIntent=new Intent(this,NotificationReceiver.class);
        addToListIntent.putExtra("rawString",tageOfNoti);
        addToListIntent.putExtra("data",title+"#"+description+"#"+reminderTime);
        addToListIntent.putExtra("id",tageOfNotiInt);
        addToListIntent.putExtra("title",title);
        addToListIntent.putExtra("description",description);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,(int)System.currentTimeMillis()/100,addToListIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification=new NotificationCompat.Builder(context,App.Channel1_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory("Channel One")
                .addAction(R.drawable.assignment,"Remind Me",pendingIntent)
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title))
                .build();
        not=NotificationManagerCompat.from(context);
        not.notify(tageOfNoti, tageOfNotiInt,notification);

    }
AlarmManager alarmManager;
    void createAnAlarm(Calendar calendar, int alarmRequestCode){
        try{
            alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            Intent alarmActivity=new Intent(this,NotificationReceiver.class);
            alarmActivity.putExtra("title","Assignment");
            alarmActivity.putExtra("description","custom time description");
            PendingIntent pendingIntent=PendingIntent.getBroadcast(this,alarmRequestCode,alarmActivity,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);



        }catch (Exception e){
            Toast.makeText(this,"Cannot Set Alarm",Toast.LENGTH_SHORT).show();
        }
    }
String wordGenerator(int customLength){
        StringBuilder sb=new StringBuilder();
        String words="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        int maxLength=words.length();
        for(int i=0;i<customLength;i++){
            int randomNum = ThreadLocalRandom.current().nextInt(0, maxLength);
            sb.append(words.charAt(randomNum));
        }
       return sb.toString();
}


}


