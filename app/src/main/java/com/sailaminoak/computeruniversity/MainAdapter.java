package com.sailaminoak.computeruniversity;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainAdapter extends BaseAdapter {
private final Context context;
private LayoutInflater layoutInflater;
private final String[] gridviewdata;
private final int[] images;
public MainAdapter(Context context, String[] gridviewdata, int[] images){
    this.context=context;
    this.gridviewdata=gridviewdata;
    this.images=images;
}

    @Override
    public int getCount() {
        return gridviewdata.length;
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
            convertView=layoutInflater.inflate(R.layout.main_grid_view_custom_layout,null);
        }
        ImageView imageView=convertView.findViewById(R.id.image_view);
        TextView textView=convertView.findViewById(R.id.text_view);
        imageView.setImageResource(images[position]);
        textView.setText(gridviewdata[position]);
        return convertView;

    }
//    private void initView(String mQuestion,TextView mQuestionHolderTextView) {
//        Paint paint = new Paint();
//        float width = paint.measureText(mQuestion);
//        int maxLength = 70; // put whatever length you need here
//        if (width > maxLength) {
//            List<String> arrayList = null;
//            String[] array = (mQuestion.split("\\s"));
//            arrayList = Arrays.asList(array);
//            int seventyPercent = (int) (Math.round(arrayList.size() * 0.70)); // play with this if needed
//            String linebreak = arrayList.get(seventyPercent) + "\n";
//            arrayList.set(seventyPercent, linebreak);
//            mQuestion = TextUtils.join(" ", arrayList);
//            mQuestion.replace(",", " ");
//        }
//        mQuestionHolderTextView.setText(mQuestion);
//    }
}
