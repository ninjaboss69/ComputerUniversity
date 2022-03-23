package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class LoadFromDatabase extends AppCompatActivity {
    LinearLayout linearLayout;
    DatabaseHelper databaseHelper;
    String nameOfTable,title;
    TextView titleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_from_database);
        linearLayout=findViewById(R.id.linearLayout);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                nameOfTable=null;
                title=null;
            } else {
                nameOfTable = extras.getString("t");
                title=extras.getString("n");
            }
        } else {
            nameOfTable = (String) savedInstanceState.getSerializable("t");
            title=(String) savedInstanceState.getSerializable("n");
        }
        if(nameOfTable==null || title==null){
            displayToast("something terribly wrong");
        }
        titleName=findViewById(R.id.name);
        titleName.setText(title);
        databaseHelper=new DatabaseHelper(this);
       try {
           Cursor res = databaseHelper.getData("select * from " + nameOfTable);

           for (res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
               String a1 = res.getString(0);
               if (a1.length() > 9000) {
                   try {
                       byte[] b = Base64.decode(a1, Base64.DEFAULT);
                       Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                       ImageView imageView = new ImageView(LoadFromDatabase.this);
                       imageView.setImageBitmap(bitmap);
                       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                       layoutParams.setMargins(30, 15, 30, 15);
                       imageView.setLayoutParams(layoutParams);
                       imageView.setAdjustViewBounds(true);
                       imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                       linearLayout.addView(imageView);
                   } catch (Exception e) {
                       addEditText(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, a1);
                   }
               } else {
                   addEditText(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, a1);
               }
           }
       }catch (Exception e){
           Toast.makeText(LoadFromDatabase.this, "Data Not Found Exception", Toast.LENGTH_SHORT).show();
       }
    }
    @SuppressLint("ResourceType")
    public void addEditText(int width, int height, String text){
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(0,10,0,10);
        EditText a=new EditText(LoadFromDatabase.this);
        a.setLayoutParams(layoutParams);
        a.setText(text);
        a.setBackgroundResource(getResources().getColor(R.color.tran));
        a.setAutoLinkMask(Linkify.WEB_URLS);
        a.setFocusable(false);
        a.setEnabled(false);
        a.setTextColor(getResources().getColor(R.color.black));
        a.setMovementMethod(LinkMovementMethod.getInstance());
        a.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                Linkify.addLinks(s, Linkify.WEB_URLS);

            }
        });
        linearLayout.addView(a);
    }

   void displayToast(String msg){
       Toast.makeText(LoadFromDatabase.this,msg,Toast.LENGTH_SHORT).show();
   }
}