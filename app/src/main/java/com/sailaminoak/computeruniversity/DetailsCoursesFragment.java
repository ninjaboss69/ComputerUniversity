package com.sailaminoak.computeruniversity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsCoursesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsCoursesFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsCoursesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsCoursesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsCoursesFragment newInstance(String param1, String param2) {
        DetailsCoursesFragment fragment = new DetailsCoursesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            courseID=getArguments().getString("courseID");
        }
    }

    ViewPager2 viewPager2;
    static String courseID="NO ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_details_courses, container, false);
         return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        commonDynamicFragmentAdapter adapter= new commonDynamicFragmentAdapter(this);
        viewPager2=view.findViewById(R.id.viewPager2);
        viewPager2.setAdapter(adapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText("Week " + (position + 1))
        ).attach();
    }



    public static class commonDynamicFragmentAdapter extends FragmentStateAdapter{



        public commonDynamicFragmentAdapter(@NonNull Fragment fragment) {
          super(fragment);
      }

      @NonNull
      @Override
      public Fragment createFragment(int position) {
          Fragment fragment = new commonDynamicFragment();
          Bundle args = new Bundle();
          args.putInt(commonDynamicFragment.ARG_SECTION_NUMBER, position + 1);
          args.putString(commonDynamicFragment.ARG_COURSE_ID,courseID);
          fragment.setArguments(args);
          return fragment;
      }

      @Override
      public int getItemCount() {
          return 3;
      }
  }
}