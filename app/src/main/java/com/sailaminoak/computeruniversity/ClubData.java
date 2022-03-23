package com.sailaminoak.computeruniversity;

public class ClubData {
    public ClubData(){

    }
    String clubName,clubImage,clubPhoneNumber,clubEmail;

    public ClubData(String clubName, String clubImage, String clubPhoneNumber, String clubEmail) {
        this.clubName = clubName;
        this.clubImage = clubImage;
        this.clubPhoneNumber = clubPhoneNumber;
        this.clubEmail = clubEmail;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubImage() {
        return clubImage;
    }

    public void setClubImage(String clubImage) {
        this.clubImage = clubImage;
    }

    public String getClubPhoneNumber() {
        return clubPhoneNumber;
    }

    public void setClubPhoneNumber(String clubPhoneNumber) {
        this.clubPhoneNumber = clubPhoneNumber;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }
}
