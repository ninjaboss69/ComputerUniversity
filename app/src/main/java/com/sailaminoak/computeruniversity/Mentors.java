package com.sailaminoak.computeruniversity;

public class Mentors {
    String name,rank,subjects,phoneNumber,email,image;
    public Mentors(){

    }
    public Mentors(String name,String rank,String subjects,String phoneNumber,String email,String image){
        this.name=name;
        this.rank=rank;
        this.subjects=subjects;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
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

    public String getImage() {

        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
