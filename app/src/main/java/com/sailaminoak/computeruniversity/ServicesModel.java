package com.sailaminoak.computeruniversity;

public class ServicesModel {
    String text,rating,image;

    public ServicesModel() {
    }

    public ServicesModel(String text, String rating, String image) {
        this.text = text;
        this.rating = rating;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
