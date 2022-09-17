package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AcademicCalendar extends AppCompatActivity {
CalendarView calendarView;
int monthWatcher =0;
int yearWatch=2021;
Button button;
SharedPreferences sharedPreferences;
ListView listViewCalendar;
    List<EventDay> events=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_calendar);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
            events = new ArrayList<>();
            calendarView = findViewById(R.id.calendarView);
            listViewCalendar = findViewById(R.id.listViewCalendar);
            calendarView.setEvents(events);
            monthWatcher = Calendar.getInstance().get(Calendar.MONTH) + 1;
            callAfterMonthChange();
            calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
                @Override
                public void onChange() {
                    --monthWatcher;
                    if (monthWatcher < 1) {
                        monthWatcher = 12;
                    }
                    if (monthWatcher == 12) {
                        --yearWatch;
                    }
                    callAfterMonthChange();
                }
            });
            calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
                @Override
                public void onChange() {
                    ++monthWatcher;
                    if (monthWatcher > 12) {
                        monthWatcher = 1;
                        ++yearWatch;
                    }
                    callAfterMonthChange();
                }
            });
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

        }
    }
    private void callAfterMonthChange(){
       calendarSetUp(monthWatcher);
       listViewSetUp(monthWatcher);
    }
    private void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void calendarSetUp(int month){
      try {

          FirebaseDatabase.getInstance().getReference("Calendar").child(String.valueOf(month)).child("Day").addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  ArrayList<String> eventDay = new ArrayList<>();
                  for (DataSnapshot ds : snapshot.getChildren()) {
                      eventDay.add(ds.getValue(String.class));

                  }
                  events = new ArrayList<>();
                  for (int i = 0; i < eventDay.size(); i++) {
                      Calendar calendar = Calendar.getInstance();
                      calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(eventDay.get(i)));
                      calendar.set(Calendar.MONTH, month - 1);
                      calendar.set(Calendar.YEAR,2022);
                      events.add(new EventDay(calendar, R.drawable.dot, Color.parseColor("#228B22")));
                  }
                  calendarView.setEvents(events);
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
      }catch (Exception e){
          displayToast("can't get the data");
      }



    }
    private void listViewSetUp(int month){
        ArrayList<String> data;
        data=new ArrayList<>();
        data.add("No Data Available");
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,data);
        listViewCalendar.setAdapter(arrayAdapter);
      try{
          FirebaseDatabase.getInstance().getReference("Calendar").child(String.valueOf(month)).child("Lists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> dataOne=new ArrayList<>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    dataOne.add(ds.getValue(String.class));
                }
                if(dataOne.size()==0){

                }else{
                    ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_activated_1,dataOne);
                    listViewCalendar.setAdapter(arrayAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      }catch (Exception e){
          data=new ArrayList<>();
          data.add("No Data Available");
          ArrayAdapter arrayAdapter1=new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,data);
          listViewCalendar.setAdapter(arrayAdapter1);
      }
    }
}