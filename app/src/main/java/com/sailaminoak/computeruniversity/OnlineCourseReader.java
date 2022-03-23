package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;

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
        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list.add(new CourseReadModel(CourseReadModel.IMAGE_TYPE,"Hi. I display a cool image too besides the omnipresent TextView.",R.drawable.aggotalgo));
        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list.add(new CourseReadModel(CourseReadModel.VIDEO_TYPE,"luQ0JWcrsWg",R.drawable.aggotalgo));
        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        list.add(new CourseReadModel(CourseReadModel.IMAGE_TYPE,"Hi again. Another cool image here. Which one is better?",R.drawable.aggotalgo));
        list.add(new CourseReadModel(CourseReadModel.VIDEO_TYPE,"1VVY9Vdb4k0",R.drawable.aggotalgo));
        list.add(new CourseReadModel(CourseReadModel.TEXT_TYPE,"Hello. This is the Text-only View Type. Nice to meet you",0));
        CourseReadAdapter adapter=new CourseReadAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}