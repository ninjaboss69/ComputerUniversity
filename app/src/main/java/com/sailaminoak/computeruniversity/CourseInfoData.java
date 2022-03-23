package com.sailaminoak.computeruniversity;

public class CourseInfoData {
    String courseID,courseName,courseLongDescription,courseShortDescription,courseShortImage,courseLargeImage,courseInstructorID,courseRating,
            courseTotalAttendant,courseTotalChapter,courseTotalHour;
    public CourseInfoData(){

    }

    public CourseInfoData(String courseID, String courseName, String courseLongDescription, String courseShortDescription, String courseShortImage, String courseLargeImage, String courseInstructorID, String courseRating, String courseTotalAttendant, String courseTotalChapter, String courseTotalHour) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseLongDescription = courseLongDescription;
        this.courseShortDescription = courseShortDescription;
        this.courseShortImage = courseShortImage;
        this.courseLargeImage = courseLargeImage;
        this.courseInstructorID = courseInstructorID;
        this.courseRating = courseRating;
        this.courseTotalAttendant = courseTotalAttendant;
        this.courseTotalChapter = courseTotalChapter;
        this.courseTotalHour = courseTotalHour;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseLongDescription;
    }

    public void setCourseDescription(String courseLongDescription) {
        this.courseLongDescription = courseLongDescription;
    }

    public String getCourseShortDescription() {
        return courseShortDescription;
    }

    public void setCourseShortDescription(String courseShortDescription) {
        this.courseShortDescription = courseShortDescription;
    }

    public String getCourseShortImage() {
        return courseShortImage;
    }

    public void setCourseShortImage(String courseShortImage) {
        this.courseShortImage = courseShortImage;
    }

    public String getCourseLargeImage() {
        return courseLargeImage;
    }

    public void setCourseLargeImage(String courseLargeImage) {
        this.courseLargeImage = courseLargeImage;
    }

    public String getCourseInstructorID() {
        return courseInstructorID;
    }

    public void setCourseInstructorID(String courseInstructorID) {
        this.courseInstructorID = courseInstructorID;
    }

    public String getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(String courseRating) {
        this.courseRating = courseRating;
    }

    public String getCourseTotalAttendant() {
        return courseTotalAttendant;
    }

    public void setCourseTotalAttendant(String courseTotalAttendant) {
        this.courseTotalAttendant = courseTotalAttendant;
    }

    public String getCourseTotalChapter() {
        return courseTotalChapter;
    }

    public void setCourseTotalChapter(String courseTotalChapter) {
        this.courseTotalChapter = courseTotalChapter;
    }

    public String getCourseTotalHour() {
        return courseTotalHour;
    }

    public void setCourseTotalHour(String courseTotalHour) {
        this.courseTotalHour = courseTotalHour;
    }

    public String getCourseLongDescription() {
        return courseLongDescription;
    }

    public void setCourseLongDescription(String courseLongDescription) {
        this.courseLongDescription = courseLongDescription;
    }
}
