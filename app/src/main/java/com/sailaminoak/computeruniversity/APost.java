package com.sailaminoak.computeruniversity;

public class APost {
    String postTitle,videoContainsOrNot,postID,approximateTime;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getVideoContainsOrNot() {
        return videoContainsOrNot;
    }

    public void setVideoContainsOrNot(String videoContainsOrNot) {
        this.videoContainsOrNot = videoContainsOrNot;
    }
    APost(){

    }
    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(String approximateTime) {
        this.approximateTime = approximateTime;
    }

    public APost(String postTitle, String videoContainsOrNot, String postID, String approximateTime) {
        this.postTitle = postTitle;
        this.videoContainsOrNot = videoContainsOrNot;
        this.postID = postID;
        this.approximateTime = approximateTime;
    }
}
