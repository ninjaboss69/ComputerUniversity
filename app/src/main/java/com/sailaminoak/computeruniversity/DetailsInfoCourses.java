package com.sailaminoak.computeruniversity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

       // ViewingDetailsOfACourse activity=(ViewingDetailsOfACourse)getActivity();
       // courseInfoData=new CourseInfoData("123","Bitching","Long Description","course short description","Image Short","","Aye Aye Myit","3.3","12211","4","2 hours");
        LoadingDialog loadingDialog=new LoadingDialog(getActivity(),true," ");
        if(courseID==null){
            Toast.makeText(getContext(), "Course ID == null", Toast.LENGTH_SHORT).show();
        }
        else{

            FirebaseDatabase.getInstance().getReference("InstructorsData").child(courseID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    CourseInfoData infoData = snapshot.getValue(CourseInfoData.class);
                    if(infoData==null)return;
                    courseLongDescripton.setText(infoData.getCourseLongDescription());
                    courseAttendants.setText(infoData.getCourseTotalAttendant());
                    courseLongDescripton.setLineSpacing(0, 1.5f);
                    courseRating.setText(infoData.courseRating);
                    instructorName.setText(infoData.courseName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        return view;
    }
}