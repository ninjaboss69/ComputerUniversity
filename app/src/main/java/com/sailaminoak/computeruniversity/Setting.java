package com.sailaminoak.computeruniversity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Setting extends AppCompatActivity {
    ListView listViewClear;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPreferences;
    classAdapter classAdapter;
    ArrayList<String> classes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listViewClear=findViewById(R.id.listViewClear);
        ArrayList<String> clearLists=new ArrayList<>();
        clearLists.add("Delete All Downloaded Posts");
        clearLists.add("Remove Verification");
        clearLists.add("Remove All Lists");
        databaseHelper=new DatabaseHelper(this);
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
            }
        }
        GridView gridView=findViewById(R.id.grid_view);
        FirebaseDatabase.getInstance().getReference("Software").child("Classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                classes=new ArrayList<>();
                for(DataSnapshot ds:snapshot.getChildren()){
                    String data=ds.getValue(String.class);
                    classes.add(data);
                }
                classAdapter=new classAdapter(getApplication(),classes);
                gridView.setAdapter(classAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL,false);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("className",classes.get(position));
                editor.apply();
                displayToast("Class Change to "+classes.get(position));
                }
                catch (Exception e){
                    displayToast("Class Name Error: Set to 1CST-A");
                }
            }
        });
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1,clearLists);
        listViewClear.setAdapter(arrayAdapter);
        listViewClear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        boolean success=true;
                        for(int i=0;i<postNames.size();i++){
                            try{
                               boolean a=databaseHelper.queryData("DROP TABLE  "+postNames.get(i));
                               if(a==false){
                                   success=false;
                               }
                            }catch (Exception e){
                                success=false;
                            }
                        }
                        if(success){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("favoritePosts","");
                            editor.apply();
                            displayToast("cleared all favorite posts");
                        }else{
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("favoritePosts","");
                            editor.apply();
                            displayToast("something wrong");
                        }

                        break;
                    case 1:
                        SharedPreferences.Editor editor12=sharedPreferences.edit();
                        editor12.putBoolean("auth",false);
                        editor12.putString("userName","");
                        editor12.apply();
                        displayToast("cleared verification");
                        break;
                    case 2:
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("TodoLists","");
                        editor.apply();
                        DatabaseHelper helper=new DatabaseHelper(getApplicationContext());
                       if(helper.queryData("drop table AlarmData") && helper.queryData("drop table AssignmentData")){
                           displayToast("cleared all lists");
                       }else{
                           displayToast("cannot drop database sorry");
                       }

                        break;
                    default:
                        displayToast("something wrong");
                        break;
                }
            }
        });
    }
    void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}