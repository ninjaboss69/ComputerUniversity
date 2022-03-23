package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class canteenAdapter extends BaseAdapter {

    Context context;
    private String[] gridViewData;
    boolean canteen=false;
    public canteenAdapter(Context context,String[] gridViewData,boolean canteen){
        this.context=context;
        this.gridViewData=gridViewData;
        this.canteen=canteen;

    }


    @Override
    public int getCount() {
        return gridViewData.length;
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
        View view= LayoutInflater.from(context).inflate(R.layout.canteen_custom,null);
        ImageView imageView=view.findViewById(R.id.canteenImage);
        TextView textView=view.findViewById(R.id.canteenName);
        if(canteen){
            imageView.setImageResource(R.drawable.canteencolor);
        }else{
            imageView.setImageResource(R.drawable.club);
        }
        textView.setText(gridViewData[position]);
        return view;

    }
}
