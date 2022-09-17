package com.sailaminoak.computeruniversity;

import android.media.Rating;

public class RatingModel {
String rating,usersCount;

    RatingModel(){
       
   }

    public RatingModel(String rating, String usersCount) {
        this.rating = rating;
        this.usersCount = usersCount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(String usersCount) {
        this.usersCount = usersCount;
    }
}
