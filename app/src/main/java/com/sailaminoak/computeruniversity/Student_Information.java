package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firebase.ui.database.paging.LoadingState;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Student_Information extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    SharedPreferences sharedPreferences;
    FirebaseRecyclerPagingAdapter<Students, Student_Information.StudentsViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor__information);
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        boolean show=sharedPreferences.getBoolean("auth",false);
        if(show==false){
            CoverDialog coverDialog=new CoverDialog(this,false);
            coverDialog.showDialog();
        }else {


            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
            mRecyclerView = findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            EditText search_filed = findViewById(R.id.search_field);
            search_filed.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //DisplayToast("Searching");
                    firebaseUserSearch(search_filed.getText().toString().trim());
                }
            });
            LinearLayoutManager mManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mManager);
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Students");
            PagedList.Config config = new PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setPrefetchDistance(2)
                    .setPageSize(2)
                    .build();

            //Initialize FirebasePagingOptions
            DatabasePagingOptions<Students> options = new DatabasePagingOptions.Builder<Students>()
                    .setLifecycleOwner(this)
                    .setQuery(mDatabase, config, Students.class)
                    .build();
            mAdapter = new FirebaseRecyclerPagingAdapter<Students, Student_Information.StudentsViewHolder>(options) {
                @NonNull
                @Override
                public Student_Information.StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new Student_Information.StudentsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_profile_list, parent, false));
                }

                @Override
                protected void onBindViewHolder(@NonNull Student_Information.StudentsViewHolder holder,
                                                int position,
                                                @NonNull Students model) {
                    setScaleAnimation(holder.itemView);
                    holder.setDetails(getApplicationContext(), model.name, model.getBatch(), model.image);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showProfileDialog(v, model);
                        }
                    });

                }

                @Override
                protected void onLoadingStateChanged(@NonNull LoadingState state) {
                    switch (state) {
                        case LOADING_INITIAL:


                            DisplayToast("initial loading");

                        case LOADING_MORE:
                            break;

                        case LOADED:
                            RelativeLayout relatvieLayoutForFirstFile=findViewById(R.id.backgroundForFavoritePosts);
                            relatvieLayoutForFirstFile.setVisibility(View.INVISIBLE);
                            mSwipeRefreshLayout.setRefreshing(false);

                            break;

                        case FINISHED:
                            break;

                        case ERROR:
                            retry();
                            break;
                    }
                }

                @Override
                protected void onError(@NonNull DatabaseError databaseError) {
                    super.onError(databaseError);
                    mSwipeRefreshLayout.setRefreshing(false);
                    databaseError.toException().printStackTrace();
                }
            };
            mRecyclerView.setAdapter(mAdapter);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    mAdapter.refresh();
                }
            });
        }

    }

    public static class StudentsViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public StudentsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            de.hdodenhof.circleimageview.CircleImageView user_image = (de.hdodenhof.circleimageview.CircleImageView) mView.findViewById(R.id.profile_image);
            if(userImage.length()<=100){
                user_image.setImageResource(R.drawable.studentcolor);
            }else{
                byte[] b = Base64.decode(userImage, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                user_image.setImageBitmap(bitmap);
            }
            user_name.setText(userName);
            user_status.setText(userStatus);
        }




    }
    private void firebaseUserSearch(String searchText) {
        Query firebaseSearchQuery =  FirebaseDatabase.getInstance().getReference("Students").orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");
        FirebaseRecyclerOptions<Students> options=new FirebaseRecyclerOptions.Builder<Students>().setQuery(firebaseSearchQuery,Students.class).build();

        FirebaseRecyclerAdapter<Students, Student_Information.StudentsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Students, Student_Information.StudentsViewHolder>(options) {


            @NonNull
            @Override
            public Student_Information.StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.common_profile_list,parent,false);
                return  new Student_Information.StudentsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull Student_Information.StudentsViewHolder holder, int position, @NonNull Students model) {
                holder.setDetails(getApplication(),model.getName(),model.getBatch(),model.getImage());
                setScaleAnimation(holder.itemView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       showProfileDialog(v,model);
                    }

                });
            }
        };
        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
    public void DisplayToast(String stringToDisplay){
        Toast.makeText(Student_Information.this,stringToDisplay,Toast.LENGTH_SHORT).show();
    }
    public void showProfileDialog(View v,Students model){
        AlertDialog.Builder builder=new AlertDialog.Builder(v.getRootView().getContext());
        View view=LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.studentprofiledialog,null);
        EditText name=view.findViewById(R.id.name);
        EditText rank=view.findViewById(R.id.rank);
        EditText subjects=view.findViewById(R.id.subjects);
        EditText phoneNumber=view.findViewById(R.id.phoneNumber);
        EditText email=view.findViewById(R.id.email);
        name.setText(model.name);
        rank.setText(model.batch);
        subjects.setText(model.achievements);
        phoneNumber.setText(model.phoneNumber);
        email.setText(model.email);
        Button ok=view.findViewById(R.id.ok);
        de.hdodenhof.circleimageview.CircleImageView user_image = (de.hdodenhof.circleimageview.CircleImageView) view.findViewById(R.id.image);
        if(model.image.length()<=100){

        }else{
            byte[] b = Base64.decode(model.image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            user_image.setImageBitmap(bitmap);
        }
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        builder.setCancelable(true);
        alertDialog.show();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
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