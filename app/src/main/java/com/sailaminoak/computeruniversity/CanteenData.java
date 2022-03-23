package com.sailaminoak.computeruniversity;

public class CanteenData {
    public CanteenData(){

    }
    String canteenName,availableItems,canteenImage,phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CanteenData(String canteenName, String phoneNumber, String availableItems, String canteenImage) {
        this.canteenName = canteenName;
        this.availableItems = availableItems;
        this.canteenImage = canteenImage;
        this.phoneNumber=phoneNumber;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public String getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(String availableItems) {
        this.availableItems = availableItems;
    }

    public String getCanteenImage() {
        return canteenImage;
    }

    public void setCanteenImage(String canteenImage) {
        this.canteenImage = canteenImage;
    }
}
