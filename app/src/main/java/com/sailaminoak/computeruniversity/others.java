package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firebase.ui.database.paging.LoadingState;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link others#newInstance} factory method to
 * create an instance of this fragment.
 */
public class others extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public others() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment others.
     */
    // TODO: Rename and change types and number of parameters
    public static others newInstance(String param1, String param2) {
        others fragment = new others();
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
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabase;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    FirebaseRecyclerPagingAdapter<Team, others.OthersViewHolder> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view= inflater.inflate(R.layout.fragment_others, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mManager);
        mDatabase = FirebaseDatabase.getInstance().getReference("Reward").child("Team");
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(2)
                .setPageSize(2)
                .build();
        DatabasePagingOptions<Team> options = new DatabasePagingOptions.Builder<Team>()
                .setLifecycleOwner(this)
                .setQuery(mDatabase, config, Team.class)
                .build();
        mAdapter = new FirebaseRecyclerPagingAdapter<Team, others.OthersViewHolder>(options) {
            @NonNull
            @Override
            public others.OthersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new others.OthersViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_profile_list, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull others.OthersViewHolder holder,
                                            int position,
                                            @NonNull Team model) {
                setScaleAnimation(holder.itemView);
                holder.setDetails(getContext(),model.teamName,model.getAwardName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       showTeamDialog(model,v);
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
                        RelativeLayout relativeLayout=view.findViewById(R.id.backgroundForFavoritePosts);
                        relativeLayout.setVisibility(View.INVISIBLE);
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
       return view;
    }
    public static class OthersViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public OthersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String teamName, String teamAward){
            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_status = (TextView) mView.findViewById(R.id.status_text);
            de.hdodenhof.circleimageview.CircleImageView user_image = (de.hdodenhof.circleimageview.CircleImageView) mView.findViewById(R.id.profile_image);
            user_name.setText(teamName);
            user_status.setText(teamAward);
        }

    }
    private void displayToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
    private void showTeamDialog(Team team,View v){
        AlertDialog.Builder builder=new AlertDialog.Builder(v.getRootView().getContext());
        View view=LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.team_dialog,null);
        EditText name=view.findViewById(R.id.teamName);
        EditText award=view.findViewById(R.id.teamAward);
        EditText participants=view.findViewById(R.id.teamParticipant);
        name.setText(team.getTeamName());
        award.setText(team.getAwardName());
        participants.setText(team.getParticipantName());
        Button ok=view.findViewById(R.id.ok);
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