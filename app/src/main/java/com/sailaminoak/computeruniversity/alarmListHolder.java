package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class alarmListHolder extends RecyclerView.ViewHolder {
    ImageView alarmMenu;
    TextView alarmDay,alarmDate,alarmMonth,alarmTime,alarmDescription,alarmTag;
    public alarmListHolder(@NonNull View itemView) {
        super(itemView);
        alarmMenu=itemView.findViewById(R.id.alarmMenu);
        alarmDay=itemView.findViewById(R.id.day);
        alarmDate=itemView.findViewById(R.id.date);
        alarmMonth=itemView.findViewById(R.id.month);
        alarmTag=itemView.findViewById(R.id.alarmTag);
        alarmTime=itemView.findViewById(R.id.alarmTime);
        alarmDescription=itemView.findViewById(R.id.alarmDescription);

    }
}
