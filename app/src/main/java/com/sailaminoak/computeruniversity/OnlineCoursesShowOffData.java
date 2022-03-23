package com.sailaminoak.computeruniversity;

public class OnlineCoursesShowOffData {
    public OnlineCoursesShowOffData(){

    }
    String backgroundImage,mainTitle,subTitle,authorName,courseID,approximateTime,totalChapter,courseRating,attendantStudents,courseProgress;

    public String getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(String courseProgress) {
        this.courseProgress = courseProgress;
    }

    public OnlineCoursesShowOffData(String backgroundImage, String mainTitle, String subTitle, String authorName, String courseID, String approximateTime, String totalChapter, String courseRating, String attendantStudents, String courseProgress) {
        this.backgroundImage = backgroundImage;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.authorName = authorName;
        this.courseID = courseID;
        this.approximateTime = approximateTime;
        this.totalChapter = totalChapter;
        this.courseRating = courseRating;
        this.attendantStudents = attendantStudents;
        this.courseProgress=courseProgress;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(String approximateTime) {
        this.approximateTime = approximateTime;
    }

    public String getTotalChapter() {
        return totalChapter;
    }

    public void setTotalChapter(String totalChapter) {
        this.totalChapter = totalChapter;
    }

    public String getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(String courseRating) {
        this.courseRating = courseRating;
    }

    public String getAttendantStudents() {
        return attendantStudents;
    }

    public void setAttendantStudents(String attendantStudents) {
        this.attendantStudents = attendantStudents;
    }
}
