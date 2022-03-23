package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class classAdapter extends BaseAdapter {
    ArrayList<String> classes;
    private Context context;
    private LayoutInflater layoutInflater;
    public classAdapter(Context context,ArrayList<String> classes){
        this.classes=classes;
        this.context=context;
    }
    @Override
    public int getCount() {
        return classes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(layoutInflater==null
        ){
            layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.class_custom_layout,null);
        }

        TextView textView=convertView.findViewById(R.id.textViewForClass);
        textView.setText(classes.get(position));
        return convertView;
    }
}
