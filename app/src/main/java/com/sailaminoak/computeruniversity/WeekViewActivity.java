package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.spec.ECField;
import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EventListener;
import java.util.Locale;

import static com.sailaminoak.computeruniversity.CalendarUtils.daysInMonthArray;
import static com.sailaminoak.computeruniversity.CalendarUtils.daysInWeekArray;
import static com.sailaminoak.computeruniversity.CalendarUtils.monthYearFromDate;
import static com.sailaminoak.computeruniversity.CalendarUtils.selectedDate;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText,schedleTitle;
    private RecyclerView calendarRecyclerView,timetableRecyclerView;
    String className="1CST-A";
    SharedPreferences sharedPreferences;
    timetableAdapter adapter;
    ArrayList<oneStringParser> models;
    Calendar z;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        className=sharedPreferences.getString("className","1CST-A");
        boolean show=sharedPreferences.getBoolean("auth",false);
        timetableRecyclerView=findViewById(R.id.timetableRecycleriew);
        z=Calendar.getInstance();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        timetableRecyclerView.setLayoutManager(layoutManager);
        models=new ArrayList();
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {
            schedleTitle = findViewById(R.id.scheduleTitle);
            CalendarUtils.selectedDate = LocalDate.now();
            initWidgets();
            setWeekView();
            getDayTimetable(className, String.valueOf(selectedDate.getDayOfWeek()), selectedDate + " schedule");
        }
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousWeekAction(View view)
    {

        onBackPressed();
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
        getDayTimetable(className,String.valueOf(date.getDayOfWeek()),String.valueOf(date)+" Timetable");
    }

private void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
}
private void getDayTimetable(String className,String thatDay,String title){
    FirebaseDatabase.getInstance().getReference("Timetables").child(className).child(thatDay).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {

         try{
             timetableofDay timetable=snapshot.getValue(timetableofDay.class);
             ArrayList<oneStringParser> models=new ArrayList<>();
             models.add(new oneStringParser(timetable.subject1));
             models.add(new oneStringParser(timetable.subject2));
             models.add(new oneStringParser(timetable.subject3));
             models.add(new oneStringParser(timetable.subject4));
             models.add(new oneStringParser(timetable.subject5));
             models.add(new oneStringParser(timetable.subject6));
             schedleTitle.setText(title);
             timetableAdapter adapter=new timetableAdapter(getApplicationContext(),models);
             timetableRecyclerView.setAdapter(adapter);
        }catch (Exception e){
             ArrayList<oneStringParser> models=new ArrayList<>();
             timetableAdapter adapter=new timetableAdapter(getApplicationContext(),models);
             timetableRecyclerView.setAdapter(adapter);
             schedleTitle.setText("No Timetable Available");

         }

        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
class timetableViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView subjectName,subjectExtraData,subjectTime;
        View subjectView;
        ImageButton subjectIcon;
    public timetableViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        subjectName=mView.findViewById(R.id.subjectName);
        subjectExtraData=mView.findViewById(R.id.subjectExtraData);
        subjectTime=mView.findViewById(R.id.subjectTime);
        subjectView=mView.findViewById(R.id.subjectView);
        subjectIcon=mView.findViewById(R.id.subjectIconPending);
    }
}
class timetableAdapter extends RecyclerView.Adapter<timetableViewHolder>{
        Context c;
        ArrayList<oneStringParser> models;
         timetableAdapter(Context c, ArrayList<oneStringParser> models){
             this.c=c;
             this.models=models;
         }

    @NonNull
    @Override
    public timetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_timetable_display,parent,false);
        return new timetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull timetableViewHolder holder, int position) {
        holder.subjectName.setText(models.get(position).getString());
             switch (position){
                 case 0:
                     holder.subjectTime.setText("9:00-10:00 AM");
                     if (z.get(Calendar.HOUR_OF_DAY) > 9) {
                     holder.subjectIcon.setImageResource(R.drawable.newcheck);
                     holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));

                 }
                     break;
                 case 1:
                     holder.subjectTime.setText("10:00-11:00 AM");
                     if (z.get(Calendar.HOUR_OF_DAY) > 10) {
                         holder.subjectIcon.setImageResource(R.drawable.newcheck);
                         holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));
                     }
                     break;
                 case 2:
                     holder.subjectTime.setText("11:00-12:00 AM");
                     if (z.get(Calendar.HOUR_OF_DAY) > 11) {
                         holder.subjectIcon.setImageResource(R.drawable.newcheck);
                         holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));
                     }
                     break;
                 case 3:
                     holder.subjectTime.setText("1:00-2:00 PM");
                     if (z.get(Calendar.HOUR_OF_DAY) > 13) {
                     holder.subjectIcon.setImageResource(R.drawable.newcheck);
                         holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));
                 }
                     break;
                 case 4:
                     holder.subjectTime.setText("2:00-3:00 PM");
                     if (z.get(Calendar.HOUR_OF_DAY) > 14) {
                     holder.subjectIcon.setImageResource(R.drawable.newcheck);
                         holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));
                 }
                     break;
                 case 5:
                     holder.subjectTime.setText("3:00-4:00 PM");
                     holder.subjectView.setVisibility(View.GONE);
                     if (z.get(Calendar.HOUR_OF_DAY) > 15) {
                     holder.subjectIcon.setImageResource(R.drawable.newcheck);

                 }
                 break;
                 default:
                     holder.subjectIcon.setImageResource(R.drawable.studentcolor);
                     holder.subjectView.setBackgroundColor(getResources().getColor(R.color.colorRed_900));
                     holder.subjectName.setText("Extra Time");

             }


    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

}