package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Announcement extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Event> events=new ArrayList<>();
    ImageButton back;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
           RelativeLayout relatvieLayoutForFirstFile=findViewById(R.id.backgroundForFavoritePosts);
            recyclerView = findViewById(R.id.recyclerView);
            LinearLayoutManager layoutManager
                    = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            EventAdapter adapter = new EventAdapter(Announcement.this, events);
            recyclerView.setAdapter(adapter);
            back = findViewById(R.id.backArrow);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            Event event=new Event("sayar katat pwe","ko kyaw","haha","123","123","Taylor Swift is Mid. I think we can all agree she should stop doing music.");
            Event event1=new Event("sayar katat pwe","ko kyaw","haha","123","123","description text here. A little bit long. but not too long.description text here. A little bit long. but not too long.description text here. A little bit long. but not too long.description text here. A little bit long. but not too long.");
            events.add(event1);
            events.add(event);
            FirebaseDatabase.getInstance().getReference("Events").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Event event = ds.getValue(Event.class);
                        events.add(event);
                    }
                    EventAdapter eventAdapter = new EventAdapter(getApplication(), events);
                    if(events.size()>=1){
                        relatvieLayoutForFirstFile.setVisibility(View.INVISIBLE);
                    }
                    recyclerView.setAdapter(eventAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    class EventHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView name,time,date,location,description,speaker;
        ImageButton notiButton,notiButton1;
        public EventHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
             name=mView.findViewById(R.id.event);
             time=mView.findViewById(R.id.time);
             date=mView.findViewById(R.id.date);
             location=mView.findViewById(R.id.location);
             description=mView.findViewById(R.id.descr);
             speaker=mView.findViewById(R.id.speaker);
             notiButton=mView.findViewById(R.id.notiButton);
             notiButton1=mView.findViewById(R.id.notiButton1);
        }
    }
    class EventAdapter extends  RecyclerView.Adapter<EventHolder>{

        Context c;
        ArrayList<Event> models;
        public EventAdapter(Context c,ArrayList<Event> models){
            this.c=c;
            this.models=models;
        }

        @NonNull
        @Override
        public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_announcement,parent,false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull EventHolder holder, int position) {
            holder.name.setText(models.get(position).getName());
            holder.time.setText(models.get(position).getTime());
            holder.date.setText(models.get(position).getDate());
            holder.location.setText(models.get(position).getLocation());
            holder.description.setText(models.get(position).getDescription());
            holder.speaker.setText(models.get(position).getSpeaker());

            holder.notiButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.notiButton1.setVisibility(View.VISIBLE);
                    holder.notiButton.setVisibility(View.GONE);
                }
            });
            holder.notiButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.notiButton.setVisibility(View.VISIBLE);
                    holder.notiButton1.setVisibility(View.GONE);
                }
            });
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    private  void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }


}