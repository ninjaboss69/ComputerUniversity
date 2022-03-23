package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class courses_online extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    fragmentAdapterCoursesOnline adapterCoursesOnline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_online);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager2=findViewById(R.id.viewPager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        adapterCoursesOnline=new fragmentAdapterCoursesOnline(fragmentManager,getLifecycle());
        viewPager2.setAdapter(adapterCoursesOnline);
        viewPager2.setUserInputEnabled(false);
        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("UCSMer"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                ucsmerOnlineCourses.even=true;

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

    }
}