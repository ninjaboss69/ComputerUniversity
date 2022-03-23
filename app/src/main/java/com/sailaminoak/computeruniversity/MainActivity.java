package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment = null;
    private FrameLayout frameLayout;
    private SpaceNavigationView spaceNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getWindow().setStatusBarColor(getResources().getColor(R.color.tran));
        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Feed", R.drawable.feed));
        spaceNavigationView.addSpaceItem(new SpaceItem("Posts", R.drawable.read));
        fragment=new mainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container,fragment,"main_fragment").commit();
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                if(getSupportFragmentManager().findFragmentByTag("ucsm_news")!=null)
                    getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("ucsm_news")).commit();
                else{
                    getSupportFragmentManager().beginTransaction().add(R.id.container,new News(),"ucsm_news").commit();
                }
                if(getSupportFragmentManager().findFragmentByTag("post_fragment")!=null){
                    getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("post_fragment")).commit();
                }
                if(getSupportFragmentManager().findFragmentByTag("main_fragment")!=null){
                    getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("main_fragment")).commit();
                }
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                   switch (itemIndex){
                       case 0:
                           if(getSupportFragmentManager().findFragmentByTag("main_fragment")!=null){
                               getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("main_fragment")).commit();
                           }
                           else
                                  getSupportFragmentManager().beginTransaction().add(R.id.container,new mainFragment(),"main_fragment").commit();

                           if(getSupportFragmentManager().findFragmentByTag("post_fragment")!=null){
                               getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("post_fragment")).commit();
                           }
                           if(getSupportFragmentManager().findFragmentByTag("ucsm_news")!=null){
                               getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("ucsm_news")).commit();
                           }


                           break;
                       case 1:
                           if(getSupportFragmentManager().findFragmentByTag("post_fragment")!=null){
                               getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("post_fragment")).commit();
                           }

                           else getSupportFragmentManager().beginTransaction().add(R.id.container,new postFragment(),"post_fragment").commit();

                           if(getSupportFragmentManager().findFragmentByTag("main_fragment")!=null){
                               getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("main_fragment")).commit();
                           }
                           if(getSupportFragmentManager().findFragmentByTag("ucsm_news")!=null){
                               getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("ucsm_news")).commit();
                           }
                           break;
                       default:
                           displayToast("error loading fragment");
                   }

            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                switch (itemIndex) {
                    case 0:
                        if (getSupportFragmentManager().findFragmentByTag("main_fragment") != null) {
                            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("main_fragment")).commit();
                        } else
                            getSupportFragmentManager().beginTransaction().add(R.id.container, new mainFragment(), "main_fragment").commit();

                        if (getSupportFragmentManager().findFragmentByTag("post_fragment") != null) {
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("post_fragment")).commit();
                        }
                        if (getSupportFragmentManager().findFragmentByTag("ucsm_news") != null) {
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("ucsm_news")).commit();
                        }


                        break;
                    case 1:
                        if (getSupportFragmentManager().findFragmentByTag("post_fragment") != null) {
                            getSupportFragmentManager().beginTransaction().show(getSupportFragmentManager().findFragmentByTag("post_fragment")).commit();
                        } else
                            getSupportFragmentManager().beginTransaction().add(R.id.container, new postFragment(), "post_fragment").commit();

                        if (getSupportFragmentManager().findFragmentByTag("main_fragment") != null) {
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("main_fragment")).commit();
                        }
                        if (getSupportFragmentManager().findFragmentByTag("ucsm_news") != null) {
                            getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("ucsm_news")).commit();
                        }
                        break;
                    default:
                        displayToast("error loading fragment");
                }
            }
        });

    }
    void displayToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}