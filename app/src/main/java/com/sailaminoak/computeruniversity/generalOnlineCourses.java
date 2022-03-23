package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link generalOnlineCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class generalOnlineCourses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public generalOnlineCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment generalOnlineCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static generalOnlineCourses newInstance(String param1, String param2) {
        generalOnlineCourses fragment = new generalOnlineCourses();
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
        }
    }
    RecyclerView recyclerView,onGoingRecyclerView;
    RelativeLayout allCoursesRelativeLayout,onGoingRelativeLayout;
    TextView viewAllCS,viewAllOngoing;
    public int csVertical=0,onGoingVertical=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_general_online_courses, container, false);
        allCoursesRelativeLayout=view.findViewById(R.id.allCoursesRelativeLayout);
        onGoingRelativeLayout=view.findViewById(R.id.ongoingCoursesRelativeLayout);
        recyclerView=view.findViewById(R.id.coursesRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        viewAllCS=view.findViewById(R.id.viewAllCSCourse);
        viewAllOngoing=view.findViewById(R.id.viewAllOngoingCourse);
        OnlineCoursesShowOffData model=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","0");
        ArrayList<OnlineCoursesShowOffData> allCoursesmModels=new ArrayList<>();
        allCoursesmModels.add(model);
         allCoursesmModels.add(model);
       allCoursesmModels.add(model);
        CoursesAdapter adapter=new CoursesAdapter(getContext(),allCoursesmModels);
        recyclerView.setAdapter(adapter);
        if(allCoursesmModels.size()==0){
            allCoursesRelativeLayout.setVisibility(View.GONE);
        }

        onGoingRecyclerView=view.findViewById(R.id.ongoingCoursesRecylerView);
        LinearLayoutManager layoutManagerOngoing
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        onGoingRecyclerView.setLayoutManager(layoutManagerOngoing);
        viewAllCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(csVertical%2==0){
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    viewAllCS.setText("See Less");
                    recyclerView.setPadding(0,0,0,0);

                }else{
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    int paddingDp = 28;
                    float density = getResources().getDisplayMetrics().density;
                    int paddingPixel = (int)(paddingDp * density);
                    // view.setPadding(0,paddingPixel,0,0);
                    viewAllCS.setText("See All");
                    recyclerView.setPadding(paddingPixel,0,0,0);
                    recyclerView.setPadding(paddingPixel,0,0,0);
                }
                csVertical++;

            }
        });
        viewAllOngoing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onGoingVertical%2==0){
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
                    onGoingRecyclerView.setLayoutManager(layoutManager);
                    viewAllOngoing.setText("See Less");
                    onGoingRecyclerView.setPadding(0,0,0,0);
                }else{
                    LinearLayoutManager layoutManager
                            = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
                    onGoingRecyclerView.setLayoutManager(layoutManager);
                    int paddingDp = 28;
                    float density = getResources().getDisplayMetrics().density;
                    int paddingPixel = (int)(paddingDp * density);
                    viewAllOngoing.setText("See All");
                   // view.setPadding(0,paddingPixel,0,0);
                    onGoingRecyclerView.setPadding(paddingPixel,0,0,0);
                }
                onGoingVertical++;

            }
        });
        OnlineCoursesShowOffData onGoingModel=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","6");
        OnlineCoursesShowOffData onGoingModelO=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","0");
        OnlineCoursesShowOffData onGoingModel1=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","3");
        ArrayList<OnlineCoursesShowOffData> onGoingModels=new ArrayList<>();
        onGoingModels.add(onGoingModel);
        onGoingModels.add(onGoingModel1);
        onGoingModels.add(onGoingModelO);
        CoursesAdapter onGoingAdapter=new CoursesAdapter(getContext(),onGoingModels);
        onGoingRecyclerView.setAdapter(onGoingAdapter);
        if(onGoingModels.size()==0){
            onGoingRelativeLayout.setVisibility(View.GONE);
        }

        return view;
    }
    class coursesViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView mainTitle,subTitle,authorName,studentAttendant,courseRating,approximateTime;
        ProgressBar onGoingProgress;
        LinearLayout onGoingProgressCardView,extraDataCardView;
        RelativeLayout mainRelativeLayout;
        ImageView imageViewofCourse;
        public coursesViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            mainTitle=mView.findViewById(R.id.mainTitle);
            subTitle=mView.findViewById(R.id.subTitle);
            authorName=mView.findViewById(R.id.authorName);
            studentAttendant=mView.findViewById(R.id.studentAttendant);
            courseRating=mView.findViewById(R.id.courseRating);
            approximateTime=mView.findViewById(R.id.approximateTime);
            mainRelativeLayout=mView.findViewById(R.id.mainRelativeLayout);
            onGoingProgress=mView.findViewById(R.id.onGoingProgress);
            onGoingProgressCardView=mView.findViewById(R.id.progressbarOnlineCourses);
            extraDataCardView=mView.findViewById(R.id.extraDataCardViewOnlineCourses);
            imageViewofCourse=mView.findViewById(R.id.imageViewForShowOffCourseCardView);
        }
    }
    class CoursesAdapter extends RecyclerView.Adapter<coursesViewHolder>{
        Context context;
        ArrayList<OnlineCoursesShowOffData> models;

        public CoursesAdapter(Context context, ArrayList<OnlineCoursesShowOffData> models) {
            this.context = context;
            this.models = models;
        }

        @NonNull
        @Override
        public coursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(context).inflate(R.layout.custom_online_courses_show_off,parent,false);
            return new coursesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull coursesViewHolder holder, int position) {
            holder.subTitle.setText(models.get(position).getSubTitle());
            holder.mainTitle.setText(models.get(position).getMainTitle());
            holder.authorName.setText(models.get(position).getAuthorName());
            holder.approximateTime.setText(models.get(position).getApproximateTime());
            holder.courseRating.setText(models.get(position).getCourseRating());
            holder.studentAttendant.setText(models.get(position).getAttendantStudents());
            holder.imageViewofCourse.setImageResource(R.drawable.aggotalgo);
            if(!models.get(position).courseProgress.contentEquals("0")){

               try{
                   holder.onGoingProgressCardView.setVisibility(View.VISIBLE);
                   holder.extraDataCardView.setVisibility(View.GONE);
                   holder.onGoingProgress.setProgress(Integer.parseInt(models.get(position).getCourseProgress()));
               }catch (Exception e){

               }

            }
            holder.mainRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(getContext(),ViewingDetailsOfACourse.class);
                    intent.putExtra("courseName",models.get(position).getMainTitle());
                    intent.putExtra("courseID",models.get(position).getCourseID());
                    intent.putExtra("courseProgress",models.get(position).getCourseProgress());
                    intent.putExtra("courseShortDescription",models.get(position).getSubTitle());
                    startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }
    void displayToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }


}