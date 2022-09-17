package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

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
    LinearLayout ongoing,cs;
    public int csVertical=0,onGoingVertical=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_general_online_courses, container, false);
        allCoursesRelativeLayout=view.findViewById(R.id.allCoursesRelativeLayout);
        onGoingRelativeLayout=view.findViewById(R.id.ongoingCoursesRelativeLayout);
        ongoing=view.findViewById(R.id.onGoingCourseTittle);
        cs=view.findViewById(R.id.firstTitleLinearLayout);
        recyclerView=view.findViewById(R.id.coursesRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        viewAllCS=view.findViewById(R.id.viewAllCSCourse);
        viewAllOngoing=view.findViewById(R.id.viewAllOngoingCourse);
        SharedPreferencesHolder holder=new SharedPreferencesHolder(getContext());
       // OnlineCoursesShowOffData model=new OnlineCoursesShowOffData("background image","Algorithm and Data Structure","Learn in Java","by Prof. Swe Swe Win","123","8 hours","13 chapter","4.8","1222","0");
        ArrayList<OnlineCoursesShowOffData> allCoursesmModels=new ArrayList<>();
//        allCoursesmModels.add(model);
//        allCoursesmModels.add(model);
//        allCoursesmModels.add(model);

        HashSet<String> enrolledCourseKeys=new HashSet<>();
        String[] courses=holder.unpackString(holder.getValueFromKey("enrollCourse"),"#");
        for(String course:courses){
            String[] eachCourse=course.split(":");
            if(eachCourse.length!=3)continue;
            enrolledCourseKeys.add(eachCourse[0]);
        }

        FirebaseDatabase.getInstance().getReference("Courses").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    OnlineCoursesShowOffData showOff=  ds.getValue(OnlineCoursesShowOffData.class);
                    if(enrolledCourseKeys.contains(showOff.getCourseID())){
                        continue;
                    }
                    showOff.setCourseProgress("0");
                    allCoursesmModels.add(showOff);

                }
                CoursesAdapter adapter=new CoursesAdapter(getContext(),allCoursesmModels);
                recyclerView.setAdapter(adapter);
                if(allCoursesmModels.size()==0){
                    allCoursesRelativeLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        ArrayList<OnlineCoursesShowOffData> onGoingModels=new ArrayList<>();
       //get from local storage

        String[] onGoingCoures=holder.unpackString(holder.getValueFromKey("enrollCourse"),"#");
        String startDate="",courseID="",prorgress="1";
        try{
            if(!checkHasOngoing(onGoingCoures)){
                if(onGoingModels.size()==0){
                    onGoingRelativeLayout.setVisibility(View.GONE);
                    // viewAllOngoing.setVisibility(View.GONE);
                    ongoing.setVisibility(View.GONE);
                }

            }
            for (int i=0;i< onGoingCoures.length;i++) {
                String course=onGoingCoures[i];
                String[] eachCourse = course.split(":");
                if (eachCourse.length != 3) continue;
                courseID = eachCourse[0];
                prorgress = eachCourse[1];
                startDate = eachCourse[2];
              //  OnlineCoursesShowOffData model=new OnlineCoursesShowOffData();
                String finalProrgress = prorgress;
                int finalI = i;
                FirebaseDatabase.getInstance().getReference("Courses").child(courseID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        OnlineCoursesShowOffData showOff=  snapshot.getValue(OnlineCoursesShowOffData.class);
                        //showOff.setCourseProgress("5");
                        //progress should get from local storage
                        //onGoingModels.add(showOff);
                        showOff.setCourseProgress(finalProrgress);
                        //showOff.set

                        onGoingModels.add(showOff);
                        if(finalI ==onGoingCoures.length-1){
                            CoursesAdapter onGoingAdapter=new CoursesAdapter(getContext(),onGoingModels);
                            onGoingRecyclerView.setAdapter(onGoingAdapter);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        }catch (Exception e){
            //
            Log.d("course","Lee error \n "+e);
        }

        return view;
    }
    boolean checkHasOngoing(String[] onGoingCoures){

        for (int i=0;i< onGoingCoures.length;i++) {
            String course = onGoingCoures[i];
            String[] eachCourse = course.split(":");
            if (eachCourse.length != 3) continue;
           return true;
        }
        return false;
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
            holder.courseRating.setText("");
            FirebaseDatabase.getInstance().getReference("TotalRatings").child(models.get(position).getCourseID()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    RatingModel ratingModel=snapshot.getValue(RatingModel.class);
                    if(ratingModel==null)return;

                   try{
                       float ans=Float.parseFloat(ratingModel.rating)/Float.parseFloat(ratingModel.usersCount);
                       holder.courseRating.setText(String.valueOf(ans));
                   }
                    catch (Exception e){
                       //nothing just user count ==0
                        return;
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
                    getActivity().finish();

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