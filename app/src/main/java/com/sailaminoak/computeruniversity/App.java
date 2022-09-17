package com.sailaminoak.computeruniversity;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String Channel1_ID="customToDo";
    public static final String Channel2_ID="assignment";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel1=new NotificationChannel(
                    Channel1_ID,"Custom To-Do List", NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel One");

            NotificationChannel channel2=new NotificationChannel(
                    Channel2_ID,"Assignments", NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("This is Channel Two");
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel2);
            notificationManager.createNotificationChannel(channel1);

        }
    }
}
