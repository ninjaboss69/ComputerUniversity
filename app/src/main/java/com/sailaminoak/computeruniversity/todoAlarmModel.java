package com.sailaminoak.computeruniversity;

public class todoAlarmModel {
    public todoAlarmModel(){}

    String alarmDay,alarmDate,alarmMonth,alarmTime,alarmDescription,alarmTag,alarmRequestCode;

    public todoAlarmModel(String alarmDay, String alarmDate, String alarmMonth, String alarmTime, String alarmDescription, String alarmTag, String alarmRequestCode) {
        this.alarmDay = alarmDay;
        this.alarmDate = alarmDate;
        this.alarmMonth = alarmMonth;
        this.alarmTime = alarmTime;
        this.alarmDescription = alarmDescription;
        this.alarmTag = alarmTag;
        this.alarmRequestCode = alarmRequestCode;
    }

    public String getAlarmDay() {
        return alarmDay;
    }

    public void setAlarmDay(String alarmDay) {
        this.alarmDay = alarmDay;
    }

    public String getAlarmDate() {
        return alarmDate;
    }

    public void setAlarmDate(String alarmDate) {
        this.alarmDate = alarmDate;
    }

    public String getAlarmMonth() {
        return alarmMonth;
    }

    public void setAlarmMonth(String alarmMonth) {
        this.alarmMonth = alarmMonth;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmDescription() {
        return alarmDescription;
    }

    public void setAlarmDescription(String alarmDescription) {
        this.alarmDescription = alarmDescription;
    }

    public String getAlarmTag() {
        return alarmTag;
    }

    public void setAlarmTag(String alarmTag) {
        this.alarmTag = alarmTag;
    }

    public String getAlarmRequestCode() {
        return alarmRequestCode;
    }

    public void setAlarmRequestCode(String alarmRequestCode) {
        this.alarmRequestCode = alarmRequestCode;
    }
}
