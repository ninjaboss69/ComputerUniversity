package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class CourseReadAdapter extends RecyclerView.Adapter {
    private ArrayList<CourseReadModel> dataSet;
    Context mContext;
    int total_types;

    public CourseReadAdapter(ArrayList<CourseReadModel> dataSet, Context mContext) {
        this.dataSet = dataSet;
        this.mContext = mContext;
        this.total_types =dataSet.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case CourseReadModel.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_textview_reading_course, parent, false);
                return new textViewReadingCourseViewHolder(view);
            case CourseReadModel.IMAGE_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_imageview_read_course, parent, false);
                return new imageViewReadingCourseViewHolder(view);
            case CourseReadModel.VIDEO_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_youtubeplayer_read_course, parent, false);
                return new youtubePlayerReadingCourseViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CourseReadModel model=dataSet.get(position);
        if(model!=null){
            switch (model.type){
                case CourseReadModel.TEXT_TYPE:
                    ( (textViewReadingCourseViewHolder)holder).textView.setText(R.string.lorem);
                    ( (textViewReadingCourseViewHolder)holder).textView.setLineSpacing(0,1.5f);
                    break;
                    case CourseReadModel.IMAGE_TYPE:
                        ((imageViewReadingCourseViewHolder)holder).imageView.setImageResource(R.drawable.ucsmbackground);
                        ((imageViewReadingCourseViewHolder)holder).textViewForImageView.setText(model.text);
                        break;
                        case CourseReadModel.VIDEO_TYPE:
                            ((youtubePlayerReadingCourseViewHolder)holder).playButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ((youtubePlayerReadingCourseViewHolder)holder).playButton.setVisibility(View.GONE);

                                    ((youtubePlayerReadingCourseViewHolder)holder).youTubePlayer.initialize(OnlineCourseReader.api_key,
                                            new YouTubePlayer.OnInitializedListener() {
                                                @Override
                                                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                                                    youTubePlayer.loadVideo(model.text);
                                                    youTubePlayer.play();

                                                }

                                                @Override
                                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                                                    Toast.makeText(mContext, "Video player Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });

                            break;
            }
        }else{
            Toast.makeText(mContext, "error lol", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
    public class textViewReadingCourseViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public textViewReadingCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
        }
    }
    public class imageViewReadingCourseViewHolder extends RecyclerView.ViewHolder{
        TextView textViewForImageView;
        ImageView imageView;
        public imageViewReadingCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textViewForImageView=itemView.findViewById(R.id.textViewForImage);
        }
    }
    public class youtubePlayerReadingCourseViewHolder extends RecyclerView.ViewHolder{
        YouTubePlayerView youTubePlayer;
        ImageButton playButton;
        public youtubePlayerReadingCourseViewHolder(@NonNull View itemView) {
            super(itemView);
            youTubePlayer=itemView.findViewById(R.id.ytPlayer);
            playButton=itemView.findViewById(R.id.playButton);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (dataSet.get(position).type) {
            case 0:
                return CourseReadModel.TEXT_TYPE;
            case 1:
                return CourseReadModel.IMAGE_TYPE;
            case 2:
                return CourseReadModel.VIDEO_TYPE;
            default:
                return -1;
        }
    }
}
