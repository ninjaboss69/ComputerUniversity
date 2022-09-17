package com.sailaminoak.computeruniversity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class assignmentViewHolder extends RecyclerView.ViewHolder {
    TextView assignmentTitle,assignmentDescription,assignmentTime;
    public assignmentViewHolder(@NonNull View itemView) {
        super(itemView);
        assignmentTitle=itemView.findViewById(R.id.assignmentTitle);
        assignmentDescription=itemView.findViewById(R.id.assignmentDescription);
        assignmentTime=itemView.findViewById(R.id.assignmentRemindTime);
    }
}
