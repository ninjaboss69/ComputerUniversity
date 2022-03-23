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

import com.google.android.material.tabs.TabLayout;

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
        OnlineCoursesShowOffData onGoingModel=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","6");
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
            bundle.putString("courseID","fuck me lol");
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
                case R.id.menuResetProgress:
                    AlertDialog.Builder completeAlertDialog = new AlertDialog.Builder(this);
                    completeAlertDialog.setTitle("Reset Progress").setMessage("After the rest you will not be able to retrieve the progress").
                            setPositiveButton("yes", (dialog, which) -> {

                                Toast.makeText(this, "Resting The Progress", Toast.LENGTH_SHORT).show();
                            })
                            .setNegativeButton("no", (dialog, which) -> dialog.cancel()).show();
                    break;

                case R.id.menuUnEnroll:
                    AlertDialog.Builder completeAlertDialog1 = new AlertDialog.Builder(this);
                    completeAlertDialog1.setTitle("Un-enroll Course").setMessage("After Un-Enroll, you will also be remove from the Certificate Program.").
                            setPositiveButton("yes", (dialog, which) -> {

                                Toast.makeText(this, "Bye", Toast.LENGTH_LONG).show();
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
                    button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ratingBar.getRating();
                        dialog.dismiss();
                        Toast.makeText(ViewingDetailsOfACourse.this, ratingBar.getRating()+" will be rate ", Toast.LENGTH_SHORT).show();

                    }
                });
                    break;

            }
            return false;
        });
        popupMenu.show();
    }
}