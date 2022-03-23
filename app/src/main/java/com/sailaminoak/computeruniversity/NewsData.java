package com.sailaminoak.computeruniversity;

public class NewsData {
    String title,date,image;
    public NewsData(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public NewsData(String title, String date, String image) {
        this.title = title;
        this.date = date;
        this.image = image;
    }
}
