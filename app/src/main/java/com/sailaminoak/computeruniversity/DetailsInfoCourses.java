package com.sailaminoak.computeruniversity;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsInfoCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsInfoCourses extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetailsInfoCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsInfoCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsInfoCourses newInstance(String param1, String param2) {
        DetailsInfoCourses fragment = new DetailsInfoCourses();
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
TextView courseLongDescripton,instructorName,courseStartDate,courseRating,courseAttendants;
    CourseInfoData courseInfoData;
    String courseID="NO ID";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_details_info_courses, container, false);
        courseLongDescripton=view.findViewById(R.id.courseLongDescriptionTextView);
        instructorName=view.findViewById(R.id.instructorName);
        courseStartDate=view.findViewById(R.id.startDateTitle);
        courseRating=view.findViewById(R.id.ratingTextView);
        courseAttendants=view.findViewById(R.id.totalAttendantTextView);
        courseLongDescripton.setText(R.string.lorem);
        courseLongDescripton.setLineSpacing(0,1.5f);
        ViewingDetailsOfACourse activity=(ViewingDetailsOfACourse)getActivity();
        courseInfoData=new CourseInfoData("123","Bitching","\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec pharetra erat a rhoncus viverra. Sed venenatis ornare lacus, a feugiat lorem pretium ac. Morbi pulvinar quam nec lacus auctor faucibus. Nulla in massa rutrum, euismod nulla ut, venenatis massa. Aliquam laoreet, enim vestibulum porta porttitor, sapien est mattis ligula, sed luctus est quam vitae justo. Donec vitae nulla pulvinar, scelerisque dui in, sagittis velit. Vestibulum vulputate dolor sit amet purus faucibus lobortis.\n" +
                "\n" +
                "Mauris odio est, maximus vel finibus porttitor, volutpat ut lorem. Cras varius nunc sed purus auctor sagittis. Curabitur auctor tincidunt nisi. Morbi sit amet efficitur risus. Fusce a lacus risus. Proin nisi lectus, tempor ut mauris efficitur, consectetur blandit enim. Phasellus rhoncus augue vestibulum volutpat congue. Ut ut porta nunc, molestie faucibus erat. Quisque dignissim, eros vitae varius auctor, mauris ligula scelerisque est, vel pharetra tellus justo at nisi. Sed magna orci, blandit at sem non, tristique luctus est. Sed cursus in ex in euismod. Maecenas laoreet porttitor enim vel placerat. Nullam euismod nunc nec tristique accumsan.\n" +
                "\n" +
                "Phasellus magna dolor, pretium ut sapien ac, condimentum vestibulum lorem. Vivamus suscipit hendrerit libero, non condimentum nulla semper eget. Duis vitae mattis lorem, ac egestas lacus. In eget felis augue. Cras in vestibulum ipsum. In lacus justo, facilisis eget tellus vel, posuere vulputate massa. Duis mollis bibendum velit vel convallis. Fusce vel ullamcorper augue. Vivamus enim massa, cursus tincidunt semper vel, feugiat vitae diam. Sed vitae molestie neque, eu vestibulum ex. Curabitur eu ultricies ante, suscipit congue lorem. Phasellus et tellus interdum mauris ullamcorper ornare. Etiam diam dolor, malesuada ac semper in, molestie a sem. Curabitur auctor nunc dolor.","course short description","Image Short","","Aye Aye Myit","3.3","12211","4","2 hours");

        return view;
    }


}