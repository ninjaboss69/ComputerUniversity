package com.sailaminoak.computeruniversity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    NotificationManager mNotificationManager;
String notiTag="";
    private NotificationManagerCompat notificationManagerCompat;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notiTag=String.valueOf(System.currentTimeMillis());
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Assignment_From_Cloud");
      //  Intent resultIntent = new Intent(this, MainActivity.class);
       // PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
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

        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getNotification().getBody()));
        builder.setAutoCancel(true);
        builder.setPriority(Notification.PRIORITY_HIGH);
        Intent addToListIntent=new Intent(this,NotificationReceiver.class);
        addToListIntent.putExtra("rawString",notiTag);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,addToListIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setSmallIcon(R.drawable.assignment);
        builder.addAction(R.drawable.assignment,"Add To Lists",pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Assignment_From_Cloud";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Assignment_From_Cloud",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        //mNotificationManager.notify(100, builder.build());
        mNotificationManager.notify(notiTag, 0,builder.build());


    }



}


