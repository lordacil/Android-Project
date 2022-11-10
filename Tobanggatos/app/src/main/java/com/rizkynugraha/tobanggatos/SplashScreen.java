package com.rizkynugraha.tobanggatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.easyandroidanimations.library.BounceAnimation;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.blue));

//        LinearLayout lv_loading = findViewById(R.id.lv_loading);
//
//        new BounceAnimation(lv_loading)
//                .setBounceDistance(50)
//                .setDuration(3000)
//                .animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);

                startActivity(intent);

                finish();
            }
        },5000);
    }
}