package com.sailaminoak.computeruniversity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentOrderStateAdapter extends FragmentStateAdapter {
    public FragmentOrderStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle=new Bundle();
        switch (position){
            case 0:
                bundle.putString("history","noflag");
                break;
            case 1:
                bundle.putString("history","flag");
                break;
            default:
                bundle.putString("history","noflag");
        }
        Fragment fragment=new PendingOrderFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
