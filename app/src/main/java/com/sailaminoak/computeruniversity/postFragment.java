package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link postFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class postFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public postFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment postFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static postFragment newInstance(String param1, String param2) {
        postFragment fragment = new postFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
RecyclerView recyclerView;
    LoadingDialog loadingDialog;
    RelativeLayout relativeLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    ArrayList<mainCardViewModel> models=new ArrayList<mainCardViewModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                View view=inflater.inflate(R.layout.fragment_post, container, false);
                relativeLayout=view.findViewById(R.id.backgroundForFavoritePosts);
                loadingDialog=new LoadingDialog(getActivity(),true,"Please Wait");
                recyclerView=view.findViewById(R.id.mainRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        loadingDialog.startAnimationDialog();
                retreatFromShowOff();

                return  view;
    }
   class mainCardViewHolder extends RecyclerView.ViewHolder{
       View mView;
       TextView categoryName,viewAll;
       RecyclerView subRecyclerView;
       public mainCardViewHolder(@NonNull View itemView) {
           super(itemView);
           mView=itemView;
           categoryName=mView.findViewById(R.id.categoryName);
           viewAll=mView.findViewById(R.id.ViewAll);
           subRecyclerView=mView.findViewById(R.id.subRecyclerView);

       }
   }
   class mainCardViewAdapter extends RecyclerView.Adapter<mainCardViewHolder>{

        Context c;
        ArrayList<mainCardViewModel> models;
        mainCardViewAdapter(Context c,ArrayList<mainCardViewModel> models){
            this.c=c;
            this.models=models;
        }


       @NonNull
       @Override
       public mainCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

           LayoutInflater inflater = LayoutInflater.from(parent.getContext());
           View view = inflater.inflate(R.layout.showoff_cardview, parent, false);
           return  new mainCardViewHolder(view);

       }

       @Override
       public void onBindViewHolder(@NonNull mainCardViewHolder holder, int position) {

            holder.categoryName.setText(models.get(position).categoryName);
            holder.viewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(),ViewAll.class);
                    intent.putExtra("c",models.get(position).categoryName);

                    startActivity(intent);

                }
            });
           LinearLayoutManager layoutManager
                   = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
           holder.subRecyclerView.setLayoutManager(layoutManager);
            ArrayList<Notes> lists=new ArrayList<>();
            FirebaseDatabase.getInstance().getReference("ShowOff").child(models.get(position).categoryName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds:snapshot.getChildren()){
                        before_post bf=ds.getValue(before_post.class);
                        if(bf!=null){
                            String name=bf.getName();
                            Notes note=new Notes(name,ds.getKey(),bf.getCatagory());
                            lists.add(note);
                        }
                        subCardViewAdapter adapter=new subCardViewAdapter(getContext(),lists);
                        holder.subRecyclerView.setAdapter(adapter);
                        loadingDialog.closingAlertDialog();
                    }
                    if(lists.size()!=0)
                        relativeLayout.setVisibility(View.INVISIBLE);



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

       }

       @Override
       public int getItemCount() {
           return models.size();
       }
   }
   class subCardViewViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView tvForSubTitle,idTextView;
       public subCardViewViewHolder(@NonNull View itemView) {
           super(itemView);
           mView=itemView;
           tvForSubTitle=mView.findViewById(R.id.subTitle);
           idTextView=mView.findViewById(R.id.stausTextView);
       }
   }
   class subCardViewAdapter extends RecyclerView.Adapter<subCardViewViewHolder>{
        Context c;
        ArrayList<Notes> models;
        subCardViewAdapter(Context c,ArrayList<Notes> models){
            this.c=c;
            this.models=models;
        }

       @NonNull
       @Override
       public subCardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater inflater = LayoutInflater.from(parent.getContext());
           View view = inflater.inflate(R.layout.sub_cardview, parent, false);
           return  new subCardViewViewHolder(view);
       }

       @Override
       public void onBindViewHolder(@NonNull subCardViewViewHolder holder, int position) {

            holder.tvForSubTitle.setText(models.get(position).getText());
            holder.idTextView.setText(models.get(position).getTableName());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(getActivity(),Read.class);
                    intent.putExtra("t",models.get(position).getTableName());
                    intent.putExtra("c",models.get(position).getCategoryName());
                    intent.putExtra("n",models.get(position).getText());
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

   void retreatFromShowOff(){
       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ShowOff");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String categoryName=dataSnapshot.getKey();
                                mainCardViewModel model=new mainCardViewModel(categoryName);
                                models.add(model);
                   }
               mainCardViewAdapter adapter=new mainCardViewAdapter(getContext(),models);
               recyclerView.setAdapter(adapter);


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
   }
    private final static int FADE_DURATION = 1000;
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

}