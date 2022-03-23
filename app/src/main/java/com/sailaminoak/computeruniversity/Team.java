package com.sailaminoak.computeruniversity;

public class Team {
    public Team(){

    }
    String teamName,awardName,participantName;

    public Team(String teamName, String awardName, String participantName) {
        this.teamName = teamName;
        this.awardName = awardName;
        this.participantName = participantName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }
}
