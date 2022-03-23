package com.sailaminoak.computeruniversity;

public class HeaderTopicModel {
    String header,description;
    public HeaderTopicModel(String header,String description){
        this.header=header;
        this.description=description;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
