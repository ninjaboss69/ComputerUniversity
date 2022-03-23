package com.sailaminoak.computeruniversity;

public class Students {
    String name,batch,phoneNumber,email,achievements,image;
    public Students(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Students(String name, String batch, String phoneNumber, String email, String achievements, String image){
        this.name=name;
        this.batch=batch;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.achievements=achievements;
        this.image=image;
    }
}
