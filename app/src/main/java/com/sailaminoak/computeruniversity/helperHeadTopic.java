package com.sailaminoak.computeruniversity;

public class helperHeadTopic {
    public helperHeadTopic(){}
    String name,category,tableName;

    public helperHeadTopic(String name, String category, String tableName) {
        this.name = name;
        this.category = category;
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
