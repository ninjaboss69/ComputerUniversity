package com.sailaminoak.computeruniversity;

public class mainCardViewModel {
    String categoryName;

    public mainCardViewModel(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public mainCardViewModel(){

    }
}
