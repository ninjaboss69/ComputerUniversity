package com.sailaminoak.computeruniversity;

public class softwareDetails {
    String developerTeam,version,releaseDate,platform,remark,about;
    public softwareDetails(){

    }

    public softwareDetails(String developerTeam, String version, String releaseDate, String platform, String remark, String about) {
        this.developerTeam = developerTeam;
        this.version = version;
        this.releaseDate = releaseDate;
        this.platform = platform;
        this.remark = remark;
        this.about = about;
    }

    public String getDeveloperTeam() {
        return developerTeam;
    }

    public void setDeveloperTeam(String developerTeam) {
        this.developerTeam = developerTeam;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
