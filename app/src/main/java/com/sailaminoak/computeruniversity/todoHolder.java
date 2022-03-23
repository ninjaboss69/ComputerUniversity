package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class todoHolder extends RecyclerView.ViewHolder {
    TextView mainText,subText,done,day,date,month,alarmRequestCode;
    LinearLayout relativeLayout;
    ImageView options;
    public todoHolder(@NonNull View itemView) {
        super(itemView);
        this.mainText=itemView.findViewById(R.id.mainText);
        this.subText=itemView.findViewById(R.id.subText);
        this.done=itemView.findViewById(R.id.done);
        this.relativeLayout=itemView.findViewById(R.id.backgroundOfCardView);
        this.day=itemView.findViewById(R.id.day);
        this.date=itemView.findViewById(R.id.date);
        this.month=itemView.findViewById(R.id.month);
        this.options=itemView.findViewById(R.id.options);
        this.alarmRequestCode=itemView.findViewById(R.id.alarmRequestCode);
    }
}
