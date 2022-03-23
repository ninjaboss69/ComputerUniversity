package com.sailaminoak.computeruniversity;

public class registerData {
    public registerData(){}
    String mkpt,name,password;

    public registerData(String mkpt, String name, String password) {
        this.mkpt = mkpt;
        this.name = name;
        this.password = password;
    }

    public String getMkpt() {
        return mkpt;
    }

    public void setMkpt(String mkpt) {
        this.mkpt = mkpt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
