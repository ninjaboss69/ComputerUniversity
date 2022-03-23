package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Read extends AppCompatActivity {
    LinearLayout linearLayout;
    LoadingDialog loadingDialog;
    String tableName;
    String categoryName;
    String Name;
    TextView nameTextView,authourTextView,uploadTimeTextView,viewCount;
    ImageButton markAsFavorite;
    DatabaseHelper databaseHelper;
    String nameOfTable;
    SharedPreferences sharedPreferences;
    boolean decideFavoriteOrNot=false;
    boolean markAsFav=false;
    boolean tableAlreadyExists=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        markAsFavorite=findViewById(R.id.markAsFavorite);
        linearLayout=findViewById(R.id.linearLayout);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                tableName = null;
            } else {
                tableName = extras.getString("t");
                categoryName=extras.getString("c");
                Name=extras.getString("n");
            }
        } else {
            tableName = (String) savedInstanceState.getSerializable("t");
            categoryName=(String)savedInstanceState.getSerializable("c");
            Name=(String)savedInstanceState.getSerializable("n");
        }
        nameOfTable=tableName;
        authourTextView=findViewById(R.id.authorTextView);
        uploadTimeTextView=findViewById(R.id.uploadTimeTextView);
        //view count need to do some work
        viewCount=findViewById(R.id.viewCount);
        viewCount.setText("Not Available RN");
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        databaseHelper=new DatabaseHelper(this);

        try{
             Cursor res=databaseHelper.getData("select * from "+tableName);
             for( res.moveToFirst(); !res.isAfterLast(); res.moveToNext() ){
                if(res!=null) {
                     if(res.getCount()>0) {
                         markAsFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                         markAsFav=true;
                         tableAlreadyExists=true;
                         decideFavoriteOrNot=true;
                     }
                 }
             }
            boolean a=databaseHelper.queryData("DROP TABLE  "+tableName);

         }catch (Exception e){

         }
        boolean a=databaseHelper.queryData("CREATE TABLE if not exists "+nameOfTable+" ( sentence TEXT )");
        if(categoryName.contentEquals("News")){
            markAsFavorite.setImageResource(R.drawable.ic_baseline_clear_24);
        }
         markAsFavorite.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(categoryName.contentEquals("News")){

                 }else {
                     if (!markAsFav) {
                         markAsFavorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                         decideFavoriteOrNot = true;
                         displayToast("added to favorite");
                         markAsFav = true;
                     } else {
                         markAsFav = false;
                         markAsFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                         decideFavoriteOrNot = false;
                         displayToast("removed from favorite");
                     }
                 }

             }
         });
         nameTextView=findViewById(R.id.name);
         nameTextView.setText(Name);
         loadingDialog=new LoadingDialog(this,true,"Fetching from Firebase");
         loadingDialog.startAnimationDialog();
         if(tableName.length()==0){
             displayToast("table name is error");
         }
         if(Name.length()==0){
             displayToast("name is error");
         }
         if(categoryName.length()==0){
             displayToast("category is error");
         }
      try {
          FirebaseDatabase.getInstance().getReference("Posts").child(categoryName).child(Name).child(tableName).addValueEventListener(new ValueEventListener() {
              @RequiresApi(api = Build.VERSION_CODES.P)
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                      TextView textView = new TextView(Read.this);
                      final String a = dataSnapshot.child("text").getValue().toString();
                      if (a.length() == 0) continue;
                      if (a.contentEquals(" ")) continue;
                      if (a.contains("asdfjkl;yokelaminoak")) {
                          String[] postData=a.split(",,,");
                          try{
                              authourTextView.setText(postData[3]);
                              uploadTimeTextView.setText(postData[2]);
                          }catch (Exception e){

                          }
                          continue;
                      }
                      if (a.length() > 9000) {
                      databaseHelper.insertData(a,nameOfTable);
                         try {
                              byte[] b = Base64.decode(a, Base64.DEFAULT);
                              Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                              ImageView imageView = new ImageView(Read.this);
                              imageView.setImageBitmap(bitmap);
                              LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                              layoutParams.setMargins(30, 20, 30, 20);
                              imageView.setLayoutParams(layoutParams);
                              imageView.setAdjustViewBounds(true);
                              imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                              linearLayout.addView(imageView);
                          } catch (Exception e) {
                              textView.setText(a);
                              textView.setLineSpacing(0,1.5f);
                              textView.setTextSize(18);
                              Linkify.addLinks(textView,Linkify.WEB_URLS|Linkify.PHONE_NUMBERS);
                              //  Linkify.addLinks(textView,Linkify.ALL);
                              textView.setLinksClickable(true);
                              textView.setMovementMethod(LinkMovementMethod.getInstance());
                              linearLayout.addView(textView);
                          }

                      } else {
                             databaseHelper.insertData(a,nameOfTable);
                          LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                          layoutParams.setMargins(30, 15, 30, 15);
                          textView.setLayoutParams(layoutParams);
                             textView.setText(a);
                             textView.setLineSpacing(0,1.5f);
                             textView.setTextSize(18);
                             textView.setLinksClickable(true);
                          Linkify.addLinks(textView,Linkify.WEB_URLS|Linkify.PHONE_NUMBERS);
                         // Linkify.addLinks(textView,Linkify.ALL);
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                          linearLayout.addView(textView);
                      }
                  }
                  loadingDialog.closingAlertDialog();

              }


              @Override
              public void onCancelled(@NonNull DatabaseError error) {
              }
          });
      }catch (Exception e){
          displayToast("Article has been deleted.");
          loadingDialog.closingAlertDialog();
      }

    }
    void displayToast(String msg){
        Toast.makeText(Read.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(categoryName.contentEquals("News")){
            decideFavoriteOrNot=false;
        }
        if(decideFavoriteOrNot){
    if(tableAlreadyExists){

    }else {
        String mainString = sharedPreferences.getString("favoritePosts", "");
        String putBack = (Name + "#" + nameOfTable + "#") + "," + mainString;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("favoritePosts", putBack);
        editor.apply();

    }
        }else{
if(tableAlreadyExists){
    String removeString=Name+"#"+tableName+"#";
            String mainString=sharedPreferences.getString("favoritePosts","");
            String[] remover=mainString.split(",");
            String putBack="";
            for(String r:remover){
                if(!r.contentEquals(removeString)){
                    putBack+=r+",";
                }
            }
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("favoritePosts",putBack);
            editor.apply();
    databaseHelper.queryData("DROP TABLE  "+tableName);

}else{
    boolean a=databaseHelper.queryData("DROP TABLE  "+nameOfTable);
}

        }
    }
}