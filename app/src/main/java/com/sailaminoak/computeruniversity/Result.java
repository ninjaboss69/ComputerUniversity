package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    RecyclerView recyclerView;
    resultAdapter adapter;
    ArrayList<resultModel> models=new ArrayList<>();
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
            recyclerView = findViewById(R.id.recyclerView);
            final String[] mkpt = {"6167"};
            EditText editText = findViewById(R.id.inputMkpt);
            Button goButton = findViewById(R.id.goButton);
            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    models = new ArrayList<>();
                    adapter = new resultAdapter(Result.this, models);
                    LoadingDialog loadingDialog = new LoadingDialog(Result.this, true, "Fetching Results");
                    loadingDialog.startAnimationDialog();
                    recyclerView.setAdapter(adapter);
                    if (editText.getText().toString().length() == 0) {
                        Toast.makeText(Result.this, "length==0", Toast.LENGTH_SHORT).show();
                        loadingDialog.closingAlertDialog();
                    } else {
                        mkpt[0] = editText.getText().toString().trim();
                        try {
                            final boolean[] dataAvailable = {false};
                            int number = Integer.parseInt(mkpt[0]);
                            FirebaseDatabase.getInstance().getReference("Results").child(mkpt[0]).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (!snapshot.exists()) {
                                        models = new ArrayList<>();
                                        adapter = new resultAdapter(Result.this, models);
                                        recyclerView.setAdapter(adapter);
                                        loadingDialog.closingAlertDialog();
                                        Toast.makeText(Result.this, "Requested result not available right now.Try 6167 for Testing", Toast.LENGTH_LONG).show();

                                    }

                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        Results results = ds.getValue(Results.class);
                                        String semester = ds.getKey();
                                        if (results != null) {
                                            try {
                                                dataAvailable[0] = true;
                                                String[] res = results.getSubjects().split(",");
                                                if (res.length == 6) {
                                                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                                                    putIntoArrayList(semester, res[0], res[1], res[2], res[3], res[4], res[5]);
                                                    adapter = new resultAdapter(Result.this, models);
                                                    recyclerView.setAdapter(adapter);
                                                    loadingDialog.closingAlertDialog();

                                                } else {
                                                    loadingDialog.closingAlertDialog();
                                                    models = new ArrayList<>();
                                                    adapter = new resultAdapter(Result.this, models);
                                                    recyclerView.setAdapter(adapter);
                                                    recyclerView.setAdapter(adapter);
                                                    Toast.makeText(Result.this, "formatting error. Contact to the Main Department", Toast.LENGTH_LONG).show();
                                                }
                                            } catch (Exception e) {
                                                loadingDialog.closingAlertDialog();
                                                models = new ArrayList<>();
                                                adapter = new resultAdapter(Result.this, models);
                                                recyclerView.setAdapter(adapter);
                                                Toast.makeText(Result.this, "something wrong :( Contact to Main Department", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            loadingDialog.closingAlertDialog();
                                            models = new ArrayList<>();
                                            adapter = new resultAdapter(Result.this, models);
                                            recyclerView.setAdapter(adapter);
                                            Toast.makeText(Result.this, "Your Data Not Added. Contact to Main Department", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    models = new ArrayList<>();
                                    adapter = new resultAdapter(Result.this, models);
                                    recyclerView.setAdapter(adapter);
                                }
                            });

                        } catch (NumberFormatException exception) {
                            loadingDialog.closingAlertDialog();
                            Toast.makeText(Result.this, "Invalid Input Format", Toast.LENGTH_SHORT).show();
                            mkpt[0] = "6167";
                            models = new ArrayList<>();
                            adapter = new resultAdapter(Result.this, models);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            });


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setSmoothScrollbarEnabled(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new resultAdapter(this, models);
            recyclerView.setAdapter(adapter);
        }

    }
    void putIntoArrayList(String name,String one,String two,String three,String four,String five,String six){
        resultModel model=new resultModel(name,one,two,three,four,five,six);
        models.add(model);
    }
    class resultViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView name,one,two,three,four,five,six;
        public resultViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=view.findViewById(R.id.name);
            one=view.findViewById(R.id.subject1);
            two=view.findViewById(R.id.subject2);
            three=view.findViewById(R.id.subject3);
            four=view.findViewById(R.id.subject4);
            five=view.findViewById(R.id.subject5);
            six=view.findViewById(R.id.subject6);
        }

    }
    class resultAdapter extends RecyclerView.Adapter<resultViewHolder>{
        Context c;
        ArrayList<resultModel> models;
        resultAdapter(Context c,ArrayList<resultModel> models){
            this.c=c;
            this.models=models;
        }

        @NonNull
        @Override
        public resultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_result,parent,false);
            return new resultViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull resultViewHolder holder, int position) {
            holder.one.setText(models.get(position).getSubject1());
            holder.two.setText(models.get(position).getSubject2());
            holder.three.setText(models.get(position).getSubject3());
            holder.four.setText(models.get(position).getSubject4());
            holder.five.setText(models.get(position).getSubject5());
            holder.six.setText(models.get(position).getSubject6());
            holder.name.setText(models.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }
}