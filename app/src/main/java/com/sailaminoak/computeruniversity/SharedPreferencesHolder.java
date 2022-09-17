package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHolder {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SharedPreferencesHolder(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("Main",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setStringToKey(String key,String value){
        editor.putString(key,value);
        editor.apply();

    }
    public String getValueFromKey(String key){
        return sharedPreferences.getString(key,"");
    }
    public String removeStringFromKey(String key,String stringToRemove,String regexSyntax){
        String[] arr=sharedPreferences.getString(key,"").split(regexSyntax);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<arr.length;i++){
            if(!arr[i].contentEquals(stringToRemove)){
                sb.append(arr[i]);
                sb.append(regexSyntax);
            }
        }
        editor.putString(key,sb.toString());
        editor.apply();
        return sb.toString();
    }
    public void addStringToKey(String key,String stringToAdd,String regexSyntax){
        String originalString=sharedPreferences.getString(key,"");
        StringBuilder sb=new StringBuilder();
        sb.append(originalString);
        sb.append(regexSyntax);
        sb.append(stringToAdd);
        editor.putString(key,sb.toString());
        editor.apply();

    }
    public String[] unpackString(String stringToUnpack,String regexSyntax){
        return stringToUnpack.split(regexSyntax);
    }
    public void deleteKey(String key){
        editor.putString(key,"");
        editor.apply();
    }


}
