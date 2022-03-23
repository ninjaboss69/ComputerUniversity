package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class commonDynamicFragment extends Fragment{
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_COURSE_ID = "courseID";
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_common_dynamic, container, false);

        recyclerView=view.findViewById(R.id.recyclerViewOfCommonDynamicFragment);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int weekNumber=args.getInt(ARG_SECTION_NUMBER);
        ArrayList<APost> models=new ArrayList<>();


        APost aPost=new APost("Introduction","NO","889","1hour");
        APost aPost1=new APost("Introduction","NO","889","1hour");
        APost aPost2=new APost("Introduction","YES","889","1hour");
        if(weekNumber==2){
            APost aPost4=new APost("Introduction","YES","889","1hour");
            APost aPost3=new APost("Introduction","YES","889","1hour");
            APost aPost5=new APost("Introduction","YES","889","1hour");
            APost aPost6=new APost("Introduction","YES","889","1hour");
            APost aPost7=new APost("Introduction","YES","889","1hour");
            APost aPost8=new APost("Introduction","YES","889","1hour");
            models.add(aPost4);
            models.add(aPost3);
            models.add(aPost5);
            models.add(aPost6);
            models.add(aPost7);
            models.add(aPost8);
        }
        models.add(aPost);
        models.add(aPost1);
        models.add(aPost2);
        classPostAdapter adapter=new classPostAdapter(models,getContext());
        recyclerView.setAdapter(adapter);

    }
    class classPostViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView playImageView;
        TextView readingTime,mainTitle;
        CardView cardView;
        public classPostViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            cardView=view.findViewById(R.id.cardView);
            playImageView=view.findViewById(R.id.playImageView);
            readingTime=view.findViewById(R.id.readingTime);
            mainTitle=view.findViewById(R.id.mainTitle);

        }
    }
    class classPostAdapter extends RecyclerView.Adapter<classPostViewHolder>{
        ArrayList<APost> models;
        Context context;

        public classPostAdapter(ArrayList<APost> models, Context context) {
            this.models = models;
            this.context = context;
        }

        @NonNull
        @Override
        public classPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.custom_course_read_show_off,parent,false);
            return new classPostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull classPostViewHolder holder, int position) {
           holder.mainTitle.setText(models.get(position).getPostTitle());
           holder.readingTime.setText(models.get(position).getApproximateTime());
           String contains=models.get(position).videoContainsOrNot;
           if(contains.contentEquals("YES")){
               holder.playImageView.setImageResource(R.drawable.aggotalgo);
           }
           holder.cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(getContext(),OnlineCourseReader.class));
               }
           });
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }


}