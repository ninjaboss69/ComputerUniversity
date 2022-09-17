package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ViewingDetailsOfACourse extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    private OnAboutDataReceivedListener mAboutDataListener;
    String courseID,courseProgress,courseName,courseShortDescription;
    TextView mainTitle,subTitle;
    ProgressBar progressBar;
    ImageView options;
    Button enrollNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_details_of_a_course);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                courseID = null;
                courseProgress=null;
                courseName=null;
                courseShortDescription=null;
                Log.d("course","viewing details of a page doesn't receive course data");
            } else {
                courseID = extras.getString("courseID");
                courseProgress=extras.getString("courseProgress");
                courseName=extras.getString("courseName");
                courseShortDescription=extras.getString("courseShortDescription");
            }
        }
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.viewpager);
        enrollNow=findViewById(R.id.enrollButton);
        FragmentManager manager = getSupportFragmentManager();
        viewCoursesAdapter adapter = new viewCoursesAdapter(manager, getLifecycle());
        //OnlineCoursesShowOffData onGoingModel=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","6");
        viewPager2.setAdapter(adapter);
        viewPager2.setUserInputEnabled(false);
        tabLayout.addTab(tabLayout.newTab().setText("Course"));
        tabLayout.addTab(tabLayout.newTab().setText("Info"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });
        mainTitle=findViewById(R.id.mainTitle);
        subTitle=findViewById(R.id.subTitle);
        progressBar=findViewById(R.id.progress_bar);
        mainTitle.setText(courseName);
        subTitle.setText(courseShortDescription);
        options=findViewById(R.id.options);
        try{
            if(Integer.parseInt(courseProgress)==0){
                progressBar.setVisibility(View.GONE);
                options.setVisibility(View.GONE);
            }else{
                progressBar.setProgress(Integer.parseInt(courseProgress));
                enrollNow.setVisibility(View.GONE);
            }
        }catch (Exception e){

        }

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v);
            }
        });

        enrollNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    SharedPreferencesHolder holder = new SharedPreferencesHolder(getApplicationContext());
                    Long date = System.currentTimeMillis();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd / MM / yyyy", Locale.getDefault());
                    String dateStr = dateFormat.format(date);
                    String aCourse = courseID + ":" + "10"+":"+dateStr;
                    holder.addStringToKey("enrollCourse", aCourse, "#");
                    //Toast.makeText(ViewingDetailsOfACourse.this, holder.getValueFromKey("enrollCourse"), Toast.LENGTH_SHORT).show();
                    enrollNow.setVisibility(View.GONE);
                }catch (Exception e){
                    Toast.makeText(ViewingDetailsOfACourse.this, "Cannot enroll : Device Need to be at least API 29", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void setData(OnAboutDataReceivedListener mAboutDataReceivedListener){
        this.mAboutDataListener=mAboutDataReceivedListener;
    }


    class viewCoursesAdapter extends FragmentStateAdapter {

        public viewCoursesAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Bundle bundle=new Bundle();
            bundle.putString("courseID",courseID);
            switch (position) {

                case 1:
                    DetailsInfoCourses fragment= new DetailsInfoCourses();
                    fragment.setArguments(bundle);
                    return fragment;
                case 0:
                    DetailsCoursesFragment fragment1= new DetailsCoursesFragment();
                    fragment1.setArguments(bundle);
                    return fragment1;


            }
            DetailsCoursesFragment fragment1= new DetailsCoursesFragment();
            fragment1.setArguments(bundle);
            return fragment1;

        }

        @Override
        public int getItemCount() {
            return 3;
        }
    }
    public void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getApplicationContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.course_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
//                case R.id.menuResetProgress:
//                    AlertDialog.Builder completeAlertDialog = new AlertDialog.Builder(this);
//                    completeAlertDialog.setTitle("Reset Progress").setMessage("After the rest you will not be able to retrieve the progress").
//                            setPositiveButton("yes", (dialog, which) -> {
//
//                                Toast.makeText(this, "Resting The Progress", Toast.LENGTH_SHORT).show();
//                            })
//                            .setNegativeButton("no", (dialog, which) -> dialog.cancel()).show();
//                    break;

                case R.id.menuUnEnroll:
                    AlertDialog.Builder completeAlertDialog1 = new AlertDialog.Builder(this);
                    completeAlertDialog1.setTitle("Un-enroll Course").setMessage("After Un-Enroll, you will also be remove from the Certificate Program.").
                            setPositiveButton("yes", (dialog, which) -> {
                                unEnrollFromTheCourse();
                                //Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
                            })
                            .setNegativeButton("no", (dialog, which) -> dialog.cancel()).show();
                    break;
                case R.id.menuRate:
                    Dialog dialog=new Dialog(this);
                    dialog.setContentView(R.layout.custom_rating_dialog);
                    dialog.setCancelable(true);
                    dialog.show();

                    RatingBar ratingBar=dialog.findViewById(R.id.ratingBar);
                    Button button=dialog.findViewById(R.id.rateButton);
                    //button.setBackgroundColor(R.color.grey_font);
                    ratingBarHelper(ratingBar,button,dialog);

                    break;

            }
            return false;
        });
        popupMenu.show();
    }
    private void unEnrollFromTheCourse() {
                SharedPreferencesHolder holder=new SharedPreferencesHolder(getApplicationContext());
                String[] OnGoingCourses=holder.getValueFromKey("enrollCourse").split("#");
                String updatedString="";
                for(String course:OnGoingCourses){
                    String[] eachCourse=course.split(":");
                    if(eachCourse.length!=3)continue;
                    String id=eachCourse[0];

                    if(id.contentEquals(courseID)){

                        continue;
                    }
                    updatedString=updatedString+"#"+course;

                }
                holder.setStringToKey("enrollCourse",updatedString);
                Log.d("course",updatedString);
                Log.d("course",holder.getValueFromKey("enrollCourse"));
                Toast.makeText(ViewingDetailsOfACourse.this, "Successfully Un-Enrolled", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,courses_online.class));
    }

    void ratingBarHelper(RatingBar ratingBar,Button button,Dialog dialog){
        SharedPreferencesHolder holder=new SharedPreferencesHolder(getApplicationContext());
        String userName=holder.getValueFromKey("mkpt");
        FirebaseDatabase.getInstance().getReference("Ratings").child(userName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("course",snapshot.getKey()+" is the key");
                String previousRating="0";
                boolean increaseUsersCount=true;
               if(snapshot.child(courseID).getValue()!=null){
                  previousRating=(String) snapshot.child(courseID).getValue();
                  increaseUsersCount=false;
                  //Toast.makeText(ViewingDetailsOfACourse.this, "previous rating "+previousRating, Toast.LENGTH_SHORT).show();
               }
                ratingBar.setRating(Float.parseFloat(previousRating));
                String finalPreviousRating = previousRating;
                boolean finalIncreaseUsersCount = increaseUsersCount;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.getRating();
                        dialog.dismiss();
                        updateRating(userName,ratingBar.getRating(),Float.parseFloat(finalPreviousRating), finalIncreaseUsersCount);
                        return;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    void updateRating(String mkpt,float rate,float previousRating,boolean increaseUsersCount){
        FirebaseDatabase.getInstance().getReference("Ratings").child(mkpt).child(courseID).setValue(String.valueOf(rate)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //Toast.makeText(getApplicationContext(), "Rated : userID "+mkpt, Toast.LENGTH_SHORT).show();
                getTotalRating(previousRating,rate,increaseUsersCount);
            }
        });

        return;
    }
    public int updateOnlyOnce=0;
    void getTotalRating(float previousRating, float currentRating, boolean increaseUsersCount){
        FirebaseDatabase.getInstance().getReference("TotalRatings").child(courseID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    RatingModel ratingModel=null;
              try  {
                    ratingModel = snapshot.getValue(RatingModel.class);
                }catch (Exception e){
                  Log.d("dick","satpat");
              }
                    if(ratingModel==null){
                       ratingModel=new RatingModel("0","1");
                    }
                    float userCount=Float.parseFloat(ratingModel.getUsersCount());
                    float totalRating=Float.parseFloat(ratingModel.getRating());
                    totalRating-=previousRating;
                    totalRating+=currentRating;
                    if(userCount==0)return;
                 //   float finalRating=totalRating/userCount;
                    ratingModel.setRating(String.valueOf(totalRating));
                   // ratingModel.setUsersCount();
                if(increaseUsersCount){
                    String currentUsers=ratingModel.getUsersCount();
                    int i=Integer.parseInt(currentUsers);
                    ++i;
                    ratingModel.setUsersCount(String.valueOf(i));
                }
                   if(updateOnlyOnce==0)updateCourseRating(ratingModel);
                   updateOnlyOnce++;

                    return;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return;
    }
    void updateCourseRating(RatingModel ratingModel){
        FirebaseDatabase.getInstance().getReference("TotalRatings").child(courseID).setValue(ratingModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ViewingDetailsOfACourse.this, "Successfully Rated : User ID ", Toast.LENGTH_SHORT).show();
            }
        });
        return;
    }
}