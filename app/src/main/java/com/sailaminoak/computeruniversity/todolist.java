package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import petrov.kristiyan.colorpicker.ColorPicker;

public class todolist extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    FloatingActionButton floatingActionButton;
    ImageView imageViewSend,calendar,imageViewForClock,colorPicker;
    EditText editText;
    Dialog dialog;
    TextView textViewTime, textViewClock;
    RecyclerView recyclerView;
    todoAdapter adapter;
    String mTH="";
    String sTH="";
    ArrayList<todoModel> models=new ArrayList<>();
    SharedPreferences sharedPreferences;
    Calendar mainCalendar;
    int BackColor=R.color.black;
    RelativeLayout relatvieLayoutForFirstFile;
    boolean extraText=true;
    AlarmManager alarmManager;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        floatingActionButton=findViewById(R.id.addNew);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        String mainString=sharedPreferences.getString("TodoLists","");
        String[] M=mainString.split(",");
        BackColor=getResources().getColor(R.color.black);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTodoCardView();
            }
        });
        relatvieLayoutForFirstFile=findViewById(R.id.firstfileRelativeLayout);
        mainCalendar=Calendar.getInstance();
        recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        if(M.length!=0){
            for(String m:M){
                if(m.length()==0){
                }else{
                    String S[]=m.split("#");
                    adapter=new todoAdapter(todolist.this,putInto(S[0],S[1],false,S[2],S[3],S[4],S[5], S[6]), relatvieLayoutForFirstFile);
                    recyclerView.setAdapter(adapter);
                }
            }
        }
        if(models.size()!=0){
            relatvieLayoutForFirstFile.setVisibility(View.INVISIBLE);
        }
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);




    }
    void openTodoCardView(){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.todo_input_custom_layout);
        Window window=dialog.getWindow();
        if(window==null){
            getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lll=window.getAttributes();
        lll.gravity=Gravity.BOTTOM;
        window.setAttributes(lll);
        dialog.setCancelable(true);
        editText=dialog.findViewById(R.id.editTextTodo);
        imageViewForClock=dialog.findViewById(R.id.timePicker);
        textViewClock =dialog.findViewById(R.id.timeForClock);
        textViewTime =dialog.findViewById(R.id.time);
        imageViewSend=dialog.findViewById(R.id.imageSend);
        colorPicker=dialog.findViewById(R.id.colorPicker);
        colorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPicker colorPicker=new ColorPicker(todolist.this);
                ArrayList<String> colors=new ArrayList<>();
                colors.add("#FF1744");
                colors.add("#00E676");
                colors.add("#f0e407");
                colors.add("#FF6200EE");
                colorPicker.setColors(colors).setColumns(4).setRoundColorButton(true).setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        BackColor=color;

                    }

                    @Override
                    public void onCancel() {
                    }
                }).show();
            }
        });

        imageViewSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText=editText.getText().toString().trim();
                if(todoText.length()==0){
                    Toast.makeText(todolist.this,"length()==0",Toast.LENGTH_SHORT).show();
                    return;

                }
                dialog.dismiss();
                int alarmRequestCode=randomNumberWithinRange(1,10000);
                String subText;
                if(extraText){
                   subText="Remind At :  NONE";
                }else {
                    subText="Remind At : "+(mTH+"  "+sTH);
                }
                extraText=true;
                String day=String.valueOf(mainCalendar.get(Calendar.DAY_OF_WEEK));
                String date=String.valueOf(mainCalendar.get(Calendar.DAY_OF_MONTH));
                String month=String.valueOf(mainCalendar.get(Calendar.MONTH));
                adapter=new todoAdapter(todolist.this,putInto(todoText,subText,true,String.valueOf(BackColor),day,date,month,String.valueOf(alarmRequestCode)),relatvieLayoutForFirstFile);
                recyclerView.setAdapter(adapter);
                if(models.size()!=0){
                    relatvieLayoutForFirstFile.setBackgroundResource(0);
                }
                    if(mainCalendar.before(Calendar.getInstance())){
                        //do something
                    }else{
                       //start an alarm
                      createAnAlarm(mainCalendar,alarmRequestCode);
                      mainCalendar=Calendar.getInstance();
                    }
            }
        });
        imageViewForClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment hourPicker=new hourPicker();
                hourPicker.show(getSupportFragmentManager(),"Choosing Hours");
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );

            }
        });

        calendar=dialog.findViewById(R.id.calendarImage);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker=new datePicker();
                datePicker.show(getSupportFragmentManager(),"Picking Date");


            }
        });

        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );
            }
        });
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        textViewTime.setText(year+"/"+month+"/"+dayOfMonth);
        mTH= textViewTime.getText().toString().trim();
        mainCalendar.set(Calendar.YEAR,year);
        mainCalendar.set(Calendar.MONTH,month);
        mainCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        extraText=false;
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        textViewClock.setText(hourOfDay+" : "+minute);
        sTH= textViewClock.getText().toString().trim();
        mainCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        mainCalendar.set(Calendar.MINUTE,minute);
        mainCalendar.set(Calendar.SECOND,0);
        extraText=false;
    }
    public void displayDate(Calendar calendar){
        Toast.makeText(this,"Will Remind You At "+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE),Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ResourceAsColor")
    public ArrayList<todoModel> putInto(String main, String sub, boolean comeFrom, String stringColor, String day, String date, String month, String alarmRequestCode){
       //SharedPreferences sharedPreferences =todolist.getSharedPreferences("Main", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor=sharedPreferences.edit();
        switch (day){
            case "1":day="Sun";break;
            case "2":day="Mon";break;
            case "3":day="Tue";break;
            case "4":day="Wed";break;
            case "5":day="Thur";break;
            case "6":day="Fri";break;
            case "7":day="Sat";break;


        }
        switch (month){
            case "0":month="Jan";break;
            case "1":month="Feb";break;
            case "2":month="March";break;
            case "3":month="Apr";break;
            case "4":month="May";break;
            case "5":month="Jun";break;
            case "6":month="July";break;
            case "7":month="Aug";break;
            case "8":month="Sep";break;
            case "9":month="Oct";break;
            case "10":month="Nov";break;
            case "11":month="Dec";break;
        }
        String subString;
        todoModel m=new todoModel();
        m.setTitle(sub);
        m.setTime(main);
        BackColor=Integer.valueOf(stringColor);
        m.setColor(BackColor);
        m.setDone("Complete");
        m.setDay(day);
        m.setDate(date);
        m.setMonth(month);
        m.setAlarmRequestCode(String.valueOf(alarmRequestCode));
        models.add(m);
        subString=sharedPreferences.getString("TodoLists","");
        subString=subString+","+(main+"#"+ sub +"#"+BackColor+"#"+day+"#"+date+"#"+month+"#"+alarmRequestCode);
        if(comeFrom){
            editor.putString("TodoLists",subString);

        }
        editor.apply();
        BackColor=getResources().getColor(R.color.black);
        if(models.size()!=0){
            this.relatvieLayoutForFirstFile.setVisibility(View.INVISIBLE);
        }
        return  models;
    }

void addToToDoLists(){

}

    void createAnAlarm(Calendar calendar,int alarmRequestCode){
        try{
            Intent alarmActivity=new Intent(this,NotificationReceiver.class);
            alarmActivity.putExtra("title","Assignment");
            alarmActivity.putExtra("description",editText.getText().toString().trim());
            PendingIntent pendingIntent=PendingIntent.getBroadcast(this,alarmRequestCode,alarmActivity,PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            displayDate(calendar);


        }catch (Exception e){
            Toast.makeText(this,"Cannot Set Alarm",Toast.LENGTH_SHORT).show();
        }
    }
 int randomNumberWithinRange(int number,int number1){
     int randomNum = ThreadLocalRandom.current().nextInt(number, number1 + 1);
     return randomNum;
 }

}