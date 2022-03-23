package com.sailaminoak.computeruniversity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
            super(context,"favoritePost.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    public boolean queryData(String sql){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL(sql);}
        catch (Exception e){
            return false;
        }
        return true;
    }
    public boolean insertData(String sentence,String nameOfTable){
        try{
            SQLiteDatabase db=getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("sentence",sentence);
            db.insert(nameOfTable,null,values);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public Cursor getData(String sql){
        SQLiteDatabase db=getWritableDatabase();

           return db.rawQuery(sql,null );
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
