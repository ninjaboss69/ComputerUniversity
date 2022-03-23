package com.sailaminoak.computeruniversity;

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

import java.util.concurrent.ThreadLocalRandom;

public class NotificationReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManagerCompat;
    SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("abc","started");
        String rawString=intent.getStringExtra("rawString");
            if(rawString!=null) {
                sharedPreferences=context.getSharedPreferences("Main",Context.MODE_PRIVATE);
                String subString=sharedPreferences.getString("TodoLists","");
                subString=subString+","+("101"+"#"+ "Mixing with Ingredients" +"#"+R.color.colorRed_900+"#"+"Tue"+"#"+"15"+"#"+"Jan"+"#"+"789");
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("TodoLists",subString);
                editor.apply();
                notificationManagerCompat=NotificationManagerCompat.from(context);
                notificationManagerCompat.cancel(rawString,0);
                Log.d("abc","successfully put "+rawString+" to lists");
            }else{
                String title=intent.getStringExtra("title");
                String description=intent.getStringExtra("description");
                notificationManagerCompat=NotificationManagerCompat.from(context);
                sendOnChannelOne(context,title,description);
            }



    }
    private void sendOnChannelOne(Context context,String title,String description) {
        Intent activityIntent=new Intent(context,todolist.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,activityIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.assignment);
        Notification notification=new NotificationCompat.Builder(context,App.Channel1_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("It is Time!")
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory("Channel One")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setColor(Color.BLUE)
                .setLargeIcon(bitmap)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(title))
                .build();
        notificationManagerCompat.notify(String.valueOf(System.currentTimeMillis()), 0,notification);

    }



}
