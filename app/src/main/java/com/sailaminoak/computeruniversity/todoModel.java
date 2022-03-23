package com.sailaminoak.computeruniversity;

public class todoModel {
    private String title,time,done,day,date,month,alarmRequestCode;
    int color;
    public todoModel(){

    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public todoModel(String title, String time, String done, int color, String day, String date, String month,String alarmRequestCode){
        this.day=day;
        this.date=date;
        this.month=month;
        this.title=title;
        this.time=time;
        this.done=done;
        this.color=color;
        this.alarmRequestCode=alarmRequestCode;

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAlarmRequestCode() {
        return alarmRequestCode;
    }

    public void setAlarmRequestCode(String alarmRequestCode) {
        this.alarmRequestCode = alarmRequestCode;
    }
}
