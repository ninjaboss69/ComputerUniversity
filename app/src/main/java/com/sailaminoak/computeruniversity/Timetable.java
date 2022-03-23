package com.sailaminoak.computeruniversity;



public class Timetable {
    String For,monday,tuesday,wednesday,thursday,friday;
    public Timetable(){

    }

    public String getFor() {
        return For;
    }

    public void setFor(String aFor) {
        For = aFor;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public Timetable(String For, String monday, String tuesday, String wednesday, String thursday, String friday){
        this.For=For;
        this.monday=monday;
        this.tuesday=tuesday;
        this.wednesday=wednesday;
        this.thursday=thursday;
        this.friday=friday;
    }
}
