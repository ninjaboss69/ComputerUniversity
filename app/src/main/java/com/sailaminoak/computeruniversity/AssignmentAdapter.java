package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AssignmentAdapter extends RecyclerView.Adapter<assignmentViewHolder> {
    Context context;
    ArrayList<AssignmentModel> models;

    public AssignmentAdapter(Context context, ArrayList<AssignmentModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public assignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.custom_assignment_layout,parent,false);
        return new assignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull assignmentViewHolder holder, int position) {
        AssignmentModel model=models.get(position);
        holder.assignmentTitle.setText(model.getTitle());
        holder.assignmentDescription.setText(model.getDescription());
        holder.assignmentTime.setText(model.getTime());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
