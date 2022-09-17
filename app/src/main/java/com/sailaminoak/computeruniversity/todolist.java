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
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
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
    TextView textViewTime, textViewClock,assignmentTitle,headerTitle;
    RecyclerView recyclerView,recyclerViewAssignemt;
    String mTH="";
    String sTH="";
    ArrayList<todoAlarmModel> models=new ArrayList<>();
    SharedPreferences sharedPreferences;
    Calendar mainCalendar;
    int BackColor=R.color.black;
    RelativeLayout relatvieLayoutForFirstFile;
    boolean extraText=true;
    AlarmManager alarmManager;
    alarmListAdapter alarmListAdapter;
    DatabaseHelper databaseHelper;
    AssignmentAdapter assignmentAdapter;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        databaseHelper=new DatabaseHelper(this);
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
        headerTitle=findViewById(R.id.headerTitle);
        assignmentTitle=findViewById(R.id.assignmentTitle);
        relatvieLayoutForFirstFile=findViewById(R.id.firstfileRelativeLayout);
        mainCalendar=Calendar.getInstance();
        recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setReverseLayout(true);
        linearLayoutManager1.setStackFromEnd(true);
        recyclerViewAssignemt=findViewById(R.id.recyclerview_Assignment);
        recyclerViewAssignemt.setLayoutManager(linearLayoutManager1);
        recyclerView.setLayoutManager(linearLayoutManager);
       try{
           LoadListFromDB();
       }catch (Exception e){
           Log.d("abc","no list created before ");
       }
       try{
           LoadFromDBForAssignment();
       }catch (Exception e){
           Log.d("abc","error range wide");
       }
        //todoAlarmModel model=new todoAlarmModel("Tue","5","Jun","3"+":"+"45","this is testing","red","123");
        /*models.add(model);
        models.add(model);
        models.add(model);
        models.add(model);
        alarmListAdapter=new alarmListAdapter(getApplicationContext(),models);
        recyclerView.setAdapter(alarmListAdapter);
         */
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
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                String description=editText.getText().toString().trim();
                if(description.length()==0){
                    Toast.makeText(todolist.this,"length()==0",Toast.LENGTH_SHORT).show();
                    return;

                }
                dialog.dismiss();
              int alarmRequestCode=(randomNumberWithinRange(1,10000));
                String subText;
                if(extraText){
                   subText="Remind At :  NONE";
                }else {
                    subText="Remind At : "+(mTH+"  "+sTH);
                }
                extraText=true;
                String[] processedData=processedDate(mainCalendar);
                String tag="none";
                /*
                colors.add("#FF1744");
                colors.add("#00E676");
                colors.add("#f0e407");
                colors.add("#FF6200EE");
                 */
                if(BackColor==Color.parseColor("#FF1744")){
                    tag="tag : RED";
                }
                if(BackColor==Color.parseColor("#00E676")){
                    tag="tag : GREEN";
                }

                String rawData=processedData[0]+"#"+processedData[1]+"#"+processedData[2]+"#"+processedData[3]+"#"+processedData[4]+"#"+processedData[5]+"#"+description+"#"+tag+"#"+alarmRequestCode;
                try{
                    boolean a=databaseHelper.queryData("CREATE TABLE if not exists AlarmData( sentence TEXT , id TEXT)");
                    if(!a) Log.d("abc","error database lol");
                }catch (Exception e){

                    Log.d("abc","cannot create table");
                }
                databaseHelper.insertAlarmData(rawData,alarmRequestCode+"","AlarmData");
                //databaseHelper.insertID(alarmRequestCode+"","AlarmData");


               try{
                   LoadListFromDB();
               }catch (Exception e){
                   Toast.makeText(todolist.this, "deep error .....", Toast.LENGTH_SHORT).show();
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
 void LoadListFromDB(){
        ArrayList<todoAlarmModel> models=new ArrayList();
     try{
         Cursor res=databaseHelper.getData("select * from AlarmData");
         for(res.moveToFirst();!res.isAfterLast();res.moveToNext()){
             String rawData=res.getString(0);
             Log.d("abd","data in list table"+rawData);
           try{  Log.d("abd","1 column data"+res.getString(1));}catch (Exception e){
               Log.d("abd","no 1 column");
           }

             String[] data=rawData.split("#");
             todoAlarmModel model=new todoAlarmModel();
             model.setAlarmDay((data[0]));
             model.setAlarmDate((data[1]));
             model.setAlarmMonth((data[2]));
             String time=data[3]+":"+data[4]+" "+data[5];
             model.setAlarmTime(time);
             model.setAlarmDescription(data[6]);
             model.setAlarmTag(data[7]);
             model.setAlarmRequestCode(data[8]);
             models.add(model);

         }
         alarmListAdapter=new alarmListAdapter(getApplicationContext(),models);
         recyclerView.setAdapter(alarmListAdapter);
         if(models.size()!=0){
             relatvieLayoutForFirstFile.setVisibility(View.GONE);
             recyclerView.setVisibility(View.VISIBLE);
             headerTitle.setVisibility(View.VISIBLE);
         }else{
             headerTitle.setVisibility(View.GONE);
             recyclerView.setVisibility(View.GONE);
             relatvieLayoutForFirstFile.setVisibility(View.VISIBLE);
         }
         alarmListAdapter.passVal(new communicator() {
             @Override
             public void passData(int data) {
                 LoadListFromDB();
             }
         });


     }catch (Exception e){
         Log.d("abc",e.getMessage());
         headerTitle.setVisibility(View.GONE);
         recyclerView.setVisibility(View.GONE);
     }
 }
 String[] processedDate(Calendar thatCalendar){
        int day=thatCalendar.get(Calendar.DAY_OF_WEEK)-1;
        String[] ans=new String[6];
        Log.d("abc","day"+day);
        switch (day){
            case 0:ans[0]="Sun";
            break;
            case 1:ans[0]="Mon";
            break;
            case 2:ans[0]="Tue";
            break;
            case 3:ans[0]="Wed";
            break;
            case 4:ans[0]="Thur";
            break;
            case 5:ans[0]="Fri";
            break;
            case 6:ans[0]="Sat";
            break;
            default: ans[0]="NaN";

        }
        int month=thatCalendar.get(Calendar.MONTH);
        Log.d("abc","month"+month);
        ans[1]=String.valueOf(thatCalendar.get((Calendar.DATE)));
        switch (month){
            case 0:ans[2]="Jan";
            break;
            case 1:ans[2]="Feb";
            break;
            case 2:ans[2]="March";
                break;
            case 3:ans[2]="Apr";
                break;
            case 4:ans[2]="May";
                break;
            case 5:ans[2]="Jun";
                break;
            case 6:ans[2]="July";
                break;
            case 7:ans[2]="Aug";
                break;
            case 8:ans[2]="Sep";
                break;
            case 9:ans[2]="Oct";
                break;
            case 10:ans[2]="Nov";
                break;
            case 11:ans[2]="Dec";
                break;
            default:ans[2]="NaN";
                break;
        }
        int hour=thatCalendar.get(Calendar.HOUR_OF_DAY);
        if(hour>12){
            hour%=12;
        }
        ans[3]=String.valueOf(hour);
        ans[4]=String.valueOf(thatCalendar.get(Calendar.MINUTE));
        ans[5]=thatCalendar.get(Calendar.HOUR_OF_DAY)>=12 ? "PM":"AM";
        return ans;
 }
 void LoadFromDBForAssignment(){
        ArrayList<AssignmentModel> models=new ArrayList<>();
try{
     Cursor res=databaseHelper.getData("select * from AssignmentData");
     for(res.moveToFirst();!res.isAfterLast();res.moveToNext()){
         String rawData=res.getString(0);
         Log.d("abc","data in assignment table"+rawData);
         String[] data=rawData.split("#");
         Long time=Long.parseLong(data[2]);
         Calendar calendar=Calendar.getInstance();
         calendar.setTimeInMillis(time);
         Calendar currentCalendar=Calendar.getInstance();
         String fullDate="";
         int a=currentCalendar.get(Calendar.MONTH);
         int b=currentCalendar.get(Calendar.DAY_OF_MONTH);
         int c=currentCalendar.get(Calendar.YEAR);
         int d=calendar.get(Calendar.MONTH)+1;
         int e=calendar.get(Calendar.DAY_OF_MONTH);
         int f=calendar.get(Calendar.YEAR);
         if(a==d && b==e && c==f){
             fullDate="(Today)";
         }else{
             fullDate="("+e+"/"+d+"/"+f+")";
         }
         Log.d("abc","fulldate" +fullDate);
         String timeString="Will Remind At : "+calendar.get(Calendar.HOUR)+" : " +calendar.get(Calendar.MINUTE)+" "+(calendar.get(Calendar.HOUR_OF_DAY)>12?"PM":"AM")+" "+fullDate;
         AssignmentModel model=new AssignmentModel(data[0],data[1],timeString);
         models.add(model);
     }
     assignmentAdapter=new AssignmentAdapter(this,models);
     recyclerViewAssignemt.setAdapter(assignmentAdapter);
     if(models.size()!=0){
         assignmentTitle.setVisibility(View.VISIBLE);
         recyclerViewAssignemt.setVisibility(View.VISIBLE);
         relatvieLayoutForFirstFile.setVisibility(View.GONE);
     }
}catch (Exception e){
    Log.d("abc","no table created , will delete Assignment Views");
    Log.d("abc",e.getMessage());
    assignmentTitle.setVisibility(View.GONE);
    recyclerViewAssignemt.setVisibility(View.GONE);
}

 }




}