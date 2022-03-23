package com.sailaminoak.computeruniversity;

public class before_post {
    String catagory,name,check_showOff,check_news;
    public before_post(){

    }

    public before_post(String catagory, String name, String check_showOff, String check_news) {
        this.catagory = catagory;
        this.name = name;
        this.check_showOff = check_showOff;
        this.check_news = check_news;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheck_showOff() {
        return check_showOff;
    }

    public void setCheck_showOff(String check_showOff) {
        this.check_showOff = check_showOff;
    }

    public String getCheck_news() {
        return check_news;
    }

    public void setCheck_news(String check_news) {
        this.check_news = check_news;
    }
}
