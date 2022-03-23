package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> images,title,date,tableNames;

    public NewsAdapter(Context context,ArrayList<String> images,ArrayList<String> title,ArrayList<String> date,ArrayList<String> tableNames){
        this.images=images;
        this.context=context;
        this.title=title;
        this.date=date;
        this.tableNames=tableNames;
    }
    @Override
    public int getCount() {
        return images.size();
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
            convertView=layoutInflater.inflate(R.layout.news_layout,null);
        }
        TextView titleString=convertView.findViewById(R.id.titleTextView);
        TextView dateString=convertView.findViewById(R.id.dateTextView);
        ImageView imageView=convertView.findViewById(R.id.newsImageView);
        titleString.setText(title.get(position));
        dateString.setText("Upload Time : "+date.get(position));
     try{
         byte[] b = Base64.decode(images.get(position), Base64.DEFAULT);
         Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
         imageView.setImageBitmap(bitmap);
         imageView.setScaleType(ImageView.ScaleType.CENTER);
     }catch (Exception e){
         imageView.setImageResource(R.drawable.sadpanda);
     }
     convertView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(context,Read.class);
             intent.putExtra("t",tableNames.get(position));
             intent.putExtra("c","News");
             intent.putExtra("n",title.get(position));
             context.startActivity(intent);
         }
     });
        return convertView;
    }
}
