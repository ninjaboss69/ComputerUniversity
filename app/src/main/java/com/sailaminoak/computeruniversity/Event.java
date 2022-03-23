package com.sailaminoak.computeruniversity;

public class Event {
    String name,time,date,location,description,speaker;
    public  Event(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event(String name,String speaker, String time, String date, String location, String description){
        this.name=name;
        this.time=time;
        this.date=date;
        this.speaker=speaker;
        this.location=location;
        this.description=description;

    }
}

