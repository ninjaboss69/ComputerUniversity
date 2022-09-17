package com.sailaminoak.computeruniversity;

public class CourseReadModel {
    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int VIDEO_TYPE=2;

    public int type;
    public String data;
    public String text;
    public CourseReadModel(){

    }
    public CourseReadModel(int type, String text, String data)
    {
        this.type=type;
        this.data=data;
        this.text=text;
    }
}
