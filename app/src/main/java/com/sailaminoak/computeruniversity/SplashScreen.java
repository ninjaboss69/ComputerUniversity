package com.sailaminoak.computeruniversity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    TextView welcomTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        lottieAnimationView=findViewById(R.id.lottieAnimation);
        //lottieAnimationView.animate().translationY(-1600).setDuration(4000).setStartDelay(1000);
        //welcomTextView=findViewById(R.id.welcomtextview);
       // lottieAnimationView.animate().translationY(-1500).setStartDelay(1500);
       lottieAnimationView.setSpeed((float) 2.5);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        },2500);
    }
}