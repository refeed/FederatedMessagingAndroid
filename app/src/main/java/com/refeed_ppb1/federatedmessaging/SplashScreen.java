package com.refeed_ppb1.federatedmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);
        Animation scaleUp = AnimationUtils.loadAnimation(getBaseContext(), R.anim.scale_up);
        ImageView img1 = findViewById(R.id.imageView3);
        ImageView img2 = findViewById(R.id.imageView4);
        ImageView img3 = findViewById(R.id.imageView5);
        img1.startAnimation(scaleUp);
        img2.startAnimation(scaleUp);
        img3.startAnimation(scaleUp);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashScreen = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(splashScreen);
                finish();
            }
        }, 3000);
    }
}