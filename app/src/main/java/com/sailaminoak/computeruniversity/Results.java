package com.sailaminoak.computeruniversity;

public class Results {
    public Results(){

    }
    String mkpt,Class,subjects;
    public Results(String mkpt,String Class,String subjects){
        this.mkpt=mkpt;
        this.Class=Class;
        this.subjects=subjects;
    }

    public String getMkpt() {
        return mkpt;
    }

    public void setMkpt(String mkpt) {
        this.mkpt = mkpt;
    }
    public void setClass(String aClass) {
        Class = aClass;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
