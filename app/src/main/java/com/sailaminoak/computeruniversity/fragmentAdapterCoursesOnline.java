package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class fragmentAdapterCoursesOnline extends FragmentStateAdapter {
    public fragmentAdapterCoursesOnline(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager,lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       if(position==0){
           return new generalOnlineCourses();

       }else if(position==1){
           return new ucsmerOnlineCourses();
       }
       return new generalOnlineCourses();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
