package com.sailaminoak.computeruniversity;

public class Notes {
    private String noteText;
    private String tableName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Notes(String noteText, String tableName, String categoryName) {
        this.noteText = noteText;
        this.tableName = tableName;
        this.categoryName = categoryName;
    }

    private String categoryName;
    public Notes(){

    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Notes(String noteText, String tableName){
        this.noteText=noteText;
        this.tableName=tableName;
    }

   public void setText(String noteText) {
        this.noteText =noteText;
    }

   public String getText() {
        return noteText;
    }
}
