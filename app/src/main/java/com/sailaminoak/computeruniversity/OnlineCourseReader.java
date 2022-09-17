package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OnlineCourseReader extends YouTubeBaseActivity {
    RecyclerView recyclerView;
   public static String api_key="AIzaSyCnq5Q_q72WhV69W_tDEIr64HrQ3lfjEJI";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_course_reader);
        recyclerView=findViewById(R.id.recyclerView);
        ArrayList<CourseReadModel> list= new ArrayList();
        Log.d("course","in the Online Course Reader ");
        String lectureID="";
        try{
            Bundle extras = getIntent().getExtras();
            String courseId=extras.getString("courseId");
            Log.d("course",courseId+" is the course id");
            lectureID=extras.getString("postID");

        }catch (Exception e){
            Log.d("course",e+"");
        }
//
//        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",""));
//        list.add(new CourseReadModel(CourseReadModel.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",""));
//        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",""));
//        list.add(new CourseReadModel(CourseReadModel.VIDEO_TYPE,"luQ0JWcrsWg",""));
//        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",""));
//        list.add(new CourseReadModel(CourseReadModel.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",""));
//        list.add(new CourseReadModel(CourseReadModel.VIDEO_TYPE,"1VVY9Vdb4k0","R.drawable.aggotalgo"));
//        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",""));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplication(), RecyclerView.VERTICAL, false);
        LoadingDialog loadingDialog=new LoadingDialog(this,true,"Fetching Lecture");
        loadingDialog.startAnimationDialog();
        FirebaseDatabase.getInstance().getReference("Lectures").child(lectureID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    CourseReadModel model=ds.getValue(CourseReadModel.class);
                    list.add(model);
                }
                CourseReadAdapter adapter=new CourseReadAdapter(list,getApplicationContext());

                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
                loadingDialog.closingAlertDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}