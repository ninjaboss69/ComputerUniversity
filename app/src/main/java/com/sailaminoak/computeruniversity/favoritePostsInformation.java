package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class favoritePostsInformation extends AppCompatActivity {
    ListView listView;
    SharedPreferences sharedPreferences;
    int backgroundImage=0;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_posts_information);
        listView=findViewById(R.id.listView);
        ArrayList<String> postNames=new ArrayList<>();
        ArrayList<String> realNames=new ArrayList<>();
        sharedPreferences =this.getSharedPreferences("Main", Context.MODE_PRIVATE);
        String mainString=sharedPreferences.getString("favoritePosts","");
        String[] names=mainString.split(",");
        for(String name:names){
          if(name.length()!=0){
              String[] temp=name.split("#");
              realNames.add(temp[0]);
              postNames.add(temp[1]);
              backgroundImage++;
          }
        }
        relativeLayout=findViewById(R.id.backgroundForFavoritePosts);
        if(backgroundImage!=0){
            relativeLayout.setVisibility(View.INVISIBLE);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,realNames);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(favoritePostsInformation.this,LoadFromDatabase.class);
                intent.putExtra("t",postNames.get(position));
                intent.putExtra("n",realNames.get(position));
                startActivity(intent);
            }
        });

    }
    void displayToast(String msg){
        Toast.makeText(favoritePostsInformation.this,msg,Toast.LENGTH_SHORT).show();
    }
}