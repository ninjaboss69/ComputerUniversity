package com.sailaminoak.computeruniversity;

public class ucsmcoursedata {
    String courseLink,courseName,sponsoredText,courseDescription,courseImage;

    public ucsmcoursedata(String courseLink, String courseName, String sponsoredText, String courseDescription, String courseImage) {
        this.courseLink = courseLink;
        this.courseName = courseName;
        this.sponsoredText = sponsoredText;
        this.courseDescription = courseDescription;
        this.courseImage = courseImage;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSponsoredText() {
        return sponsoredText;
    }

    public void setSponsoredText(String sponsoredText) {
        this.sponsoredText = sponsoredText;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }
}
