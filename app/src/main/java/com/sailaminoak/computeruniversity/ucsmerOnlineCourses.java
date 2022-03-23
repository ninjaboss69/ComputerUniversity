package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ucsmerOnlineCourses#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ucsmerOnlineCourses extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ucsmerOnlineCourses() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ucsmerOnlineCourses.
     */
    // TODO: Rename and change types and number of parameters
    public static ucsmerOnlineCourses newInstance(String param1, String param2) {
        ucsmerOnlineCourses fragment = new ucsmerOnlineCourses();
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
    RecyclerView recyclerView;
    public static boolean even=true;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_ucsmer_online_courses, container, false);
       recyclerView=view.findViewById(R.id.recyclerView);
       ucsmcoursedata model=new ucsmcoursedata("https://ucsm.edu.mm","Learning Management System","*sponsored by ucsm","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla ornare massa in aliquet. Ut sed nisl non odio viverra vehicula. Nunc a aliquet libero. Cras odio magna, feugiat non elementum sit amet, blandit quis risus. Quisque pharetra, tellus in ornare sodales, turpis neque iaculis lorem, at luctus ipsum elit a nunc. Cras laoreet ante risus, a ornare orci varius ac. Quisque a pellentesque libero. Duis dolor enim, bibendum eget cursus at, rhoncus a massa. Aliquam enim sapien, tincidunt sit amet faucibus at, tincidunt at leo. Proin nec erat eu nisi semper varius. Donec eget orci fermentum, rutrum diam non, sodales velit. Pellentesque vitae mi sem. Sed placerat ut mauris vitae eleifend. Nulla laoreet justo pharetra ex tempor ultrices. Donec in mi pellentesque, ultricies ligula in, porta ex. Fusce arcu dui, varius eget pharetra vel, commodo vitae arcu.","");
        ucsmcoursedata model1=new ucsmcoursedata("https://ucsm.edu.mm","UCSM on Coursera","*sponsored by coursera","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla ornare massa in aliquet. Ut sed nisl non odio viverra vehicula. Nunc a aliquet libero. Cras odio magna, feugiat non elementum sit amet, blandit quis risus. Quisque pharetra, tellus in ornare sodales, turpis neque iaculis lorem, at luctus ipsum elit a nunc. Cras laoreet ante risus, a ornare orci varius ac. Quisque a pellentesque libero. Duis dolor enim, bibendum eget cursus at, rhoncus a massa. Aliquam enim sapien, tincidunt sit amet faucibus at, tincidunt at leo. Proin nec erat eu nisi semper varius. Donec eget orci fermentum, rutrum diam non, sodales velit. Pellentesque vitae mi sem. Sed placerat ut mauris vitae eleifend. Nulla laoreet justo pharetra ex tempor ultrices. Donec in mi pellentesque, ultricies ligula in, porta ex. Fusce arcu dui, varius eget pharetra vel, commodo vitae arcu.","");
        ucsmcoursedata model2=new ucsmcoursedata("https://ucsm.edu.mm","Data Analyst","*sponsored by yuue","hello course text descriptino","");
        ucsmcoursedata model3=new ucsmcoursedata("https://ucsm.edu.mm","Learning Management System","*sponsored by ucsm","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla ornare massa in aliquet. Ut sed nisl non odio viverra vehicula. Nunc a aliquet libero. Cras odio magna, feugiat non elementum sit amet, blandit quis risus. Quisque pharetra, tellus in ornare sodales, turpis neque iaculis lorem, at luctus ipsum elit a nunc. Cras laoreet ante risus, a ornare orci varius ac. Quisque a pellentesque libero. Duis dolor enim, bibendum eget cursus at, rhoncus a massa. Aliquam enim sapien, tincidunt sit amet faucibus at, tincidunt at leo. Proin nec erat eu nisi semper varius. Donec eget orci fermentum, rutrum diam non, sodales velit. Pellentesque vitae mi sem. Sed placerat ut mauris vitae eleifend. Nulla laoreet justo pharetra ex tempor ultrices. Donec in mi pellentesque, ultricies ligula in, porta ex. Fusce arcu dui, varius eget pharetra vel, commodo vitae arcu.","");
        ucsmcoursedata model4=new ucsmcoursedata("https://ucsm.edu.mm","UCSM on Coursera-1","*sponsored by coursera","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam fringilla ornare massa in aliquet. Ut sed nisl non odio viverra vehicula. Nunc a aliquet libero. Cras odio magna, feugiat non elementum sit amet, blandit quis risus. Quisque pharetra, tellus in ornare sodales, turpis neque iaculis lorem, at luctus ipsum elit a nunc. Cras laoreet ante risus, a ornare orci varius ac. Quisque a pellentesque libero. Duis dolor enim, bibendum eget cursus at, rhoncus a massa. Aliquam enim sapien, tincidunt sit amet faucibus at, tincidunt at leo. Proin nec erat eu nisi semper varius. Donec eget orci fermentum, rutrum diam non, sodales velit. Pellentesque vitae mi sem. Sed placerat ut mauris vitae eleifend. Nulla laoreet justo pharetra ex tempor ultrices. Donec in mi pellentesque, ultricies ligula in, porta ex. Fusce arcu dui, varius eget pharetra vel, commodo vitae arcu.","");
        ucsmcoursedata model5=new ucsmcoursedata("https://ucsm.edu.mm","News and Agency","*sponsored by ucsm","hello course text descriptino","");
        ArrayList<ucsmcoursedata> models=new ArrayList<>();
        models.add(model);
        models.add(model1);
        models.add(model2);
        models.add(model3);
        models.add(model4);
        models.add(model5);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recylerViewAdapter adapter=new recylerViewAdapter(getContext(),models);
        recyclerView.setAdapter(adapter);
        return view;
    }
    class recyclerViewHolder extends RecyclerView.ViewHolder{
        TextView courseName,sponsoredText,textViewOfUCSMCourse;
                ImageView courseImage;
                ImageButton expandMore;
                LinearLayout childLinearLayout;
                Button goButton;
                CardView cardView;
        public recyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName=itemView.findViewById(R.id.courseName);
            sponsoredText=itemView.findViewById(R.id.sponsoredText);
            courseImage=itemView.findViewById(R.id.courseImage);
            expandMore=itemView.findViewById(R.id.expandMore);
            childLinearLayout=itemView.findViewById(R.id.childLinearLayout);
            textViewOfUCSMCourse=itemView.findViewById(R.id.textViewOfUCSMCourse);
            goButton=itemView.findViewById(R.id.goButton);
            cardView=itemView.findViewById(R.id.parentCardView);

        }
    }
    class recylerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder>{
        Context context;
       ArrayList<ucsmcoursedata> models;

        public recylerViewAdapter(Context context, ArrayList<ucsmcoursedata> models) {
            this.context = context;
            this.models = models;
        }

        @NonNull
        @Override
        public recyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(context).inflate(R.layout.card_view_course_ucsm,parent,false);
            return new recyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull recyclerViewHolder holder, int position) {
        ucsmcoursedata model=models.get(position);
        holder.courseName.setText(model.getCourseName());
        holder.textViewOfUCSMCourse.setText((model.getCourseDescription()));
        holder.textViewOfUCSMCourse.setLineSpacing(0,1.5f);
        holder.sponsoredText.setText(model.getSponsoredText());
        holder.courseImage.setImageResource(R.drawable.aggotalgo);
        holder.expandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              changeExpand(holder.childLinearLayout,holder.expandMore);
            }
        });
        holder.courseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeExpand(holder.childLinearLayout,holder.expandMore);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeExpand(holder.childLinearLayout,holder.expandMore);
            }
        });
        holder.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkNow(model.getCourseLink());
            }
        });

        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }
    private void openLinkNow(String desiredLink){
        try{ Uri uri = Uri.parse(desiredLink); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getContext(),"Invalid Link",Toast.LENGTH_SHORT).show();
        }
    }
    private void changeExpand(LinearLayout linearLayout,ImageButton expandMore){
        if(linearLayout.getVisibility()==View.GONE)
        {
           linearLayout.setVisibility(View.VISIBLE);
            even=false;
            expandMore.setImageResource(R.drawable.ic_baseline_expand_less_24);
        }else{
            linearLayout.setVisibility(View.GONE);
            even=true;
            expandMore.setImageResource(R.drawable.ic_baseline_expand_more_24);
        }
    }
}