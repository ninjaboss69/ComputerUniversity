package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firebase.ui.database.paging.LoadingState;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {
    String categoryName="New Category";
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    FirebaseRecyclerPagingAdapter<Notes, tableNameViewHolder> mAdapter;
    LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        loadingDialog=new LoadingDialog(this,true,"Fetching Contents");
        loadingDialog.startAnimationDialog();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {

            } else {
                categoryName=extras.getString("c");
            }
        } else {

            categoryName=(String)savedInstanceState.getSerializable("c");

        }
        mDatabase = FirebaseDatabase.getInstance().getReference("Posts").child(categoryName);
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(2)
                .setPageSize(10)
                .build();
        DatabasePagingOptions<Notes> options = new DatabasePagingOptions.Builder<Notes>()
                .setLifecycleOwner(this)
                .setQuery(mDatabase, config, Notes.class)
                .build();
        mAdapter=new FirebaseRecyclerPagingAdapter<Notes, tableNameViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull tableNameViewHolder viewHolder, int position, @NonNull Notes model) {
                setScaleAnimation(viewHolder.itemView);
                DatabaseReference reference = getRef(position);

                viewHolder.tableName.setText(reference.getKey());

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot ds:snapshot.getChildren()){
                            viewHolder.authourName.setText(ds.getKey());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent=new Intent(ViewAll.this,Read.class);

                        String a=viewHolder.authourName.getText().toString().trim();
                        if(a.length()!=0 && !a.contentEquals("fetching...")){
                            intent.putExtra("c",categoryName);
                            intent.putExtra("t",a);
                            intent.putExtra("n",reference.getKey());
                            startActivity(intent);
                        }else{
                            DisplayToast("pleas wait while loading ids");
                        }



                    }
                });
            }

            @Override
            protected void onLoadingStateChanged(@NonNull LoadingState state) {
                switch (state) {
                    case LOADING_INITIAL:
                        case LOADING_MORE:

                            break;

                    case LOADED:
                        mSwipeRefreshLayout.setRefreshing(false);
                        loadingDialog.closingAlertDialog();
                        break;

                    case FINISHED:
                        break;

                    case ERROR:
                        retry();
                        break;
                }
            }

            @NonNull
            @Override
            public tableNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new tableNameViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.category_only, parent, false));
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
    class tableNameViewHolder extends RecyclerView.ViewHolder{
        TextView tableName,authourName;
        View mView;
        public tableNameViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;
            tableName=mView.findViewById(R.id.TableNameTextView);
            authourName=mView.findViewById(R.id.authorName);


        }
    }
    void DisplayToast(String msg){
        Toast.makeText(ViewAll.this,msg,Toast.LENGTH_SHORT).show();
    }
    private final static int FADE_DURATION = 1000;
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
}