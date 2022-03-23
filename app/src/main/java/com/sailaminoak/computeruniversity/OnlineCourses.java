package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OnlineCourses extends AppCompatActivity {

RecyclerView recyclerView;
ArrayList<Link> arrayData=new ArrayList<>();
LoadingDialog loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_courses);
        recyclerView=findViewById(R.id.recyclerView);
        loadingDialog=new LoadingDialog(this,true,"Fetching Courses Data");
        loadingDialog.startAnimationDialog();
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        onlineCoursesAdapter adapter=new onlineCoursesAdapter(this,arrayData);
        recyclerView.setAdapter(adapter);

   FirebaseDatabase.getInstance().getReference("Links").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    //Toast.makeText(getApplicationContext(),ds.getKey(),Toast.LENGTH_SHORT).show();
                    Link link123123=ds.getValue(Link.class);
                    arrayData.add(link123123);
                }
                onlineCoursesAdapter adapter=new onlineCoursesAdapter(getApplication(),arrayData);
                recyclerView.setAdapter(adapter);
                loadingDialog.closingAlertDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                    loadingDialog.closingAlertDialog();

            }
        });


    }
    class onlineCoursesViewHolder extends RecyclerView.ViewHolder{
        View view;
        TextView title;
        ImageView imageView;
        public onlineCoursesViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
           title=view.findViewById(R.id.linkTitle);
            imageView=view.findViewById(R.id.linkImage);



        }
    }
    class onlineCoursesAdapter extends RecyclerView.Adapter<onlineCoursesViewHolder>{

        @NonNull
        @Override
        public onlineCoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_custom_layout,parent,false);
            return new onlineCoursesViewHolder(view);
        }
        Context context;
        ArrayList<Link> models;
        public onlineCoursesAdapter(Context context,ArrayList<Link> models){
            this.context=context;
            this.models=models;
        }

        @Override
        public void onBindViewHolder(@NonNull onlineCoursesViewHolder holder, int position) {
         holder.title.setText(models.get(position).getTitle());
          String imageData=models.get(position).getImage();
          if(imageData.length()<1000){

          }else{
              byte[] b = Base64.decode(imageData, Base64.DEFAULT);
              Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
              holder.imageView.setImageBitmap(bitmap);
          }
          holder.imageView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  openLinkNow(models.get(position).getLink());
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
         Toast.makeText(this,"Invalid Link",Toast.LENGTH_SHORT).show();
     }
  }

}