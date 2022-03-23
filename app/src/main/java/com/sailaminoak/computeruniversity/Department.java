package com.sailaminoak.computeruniversity;

public class Department {
    String departmentName,phoneNumber,emailAddress;
    public  Department(){

    }
    public Department(String departmentName,String phoneNumber,String emailAddress){
        this.departmentName=departmentName;
        this.phoneNumber=phoneNumber;
        this.emailAddress=emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
