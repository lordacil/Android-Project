package com.rizkynugraha.cari_kos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.easyandroidanimations.library.BounceAnimation;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        LinearLayout lv_loading = findViewById(R.id.lv_loading);

        new BounceAnimation(lv_loading)
                .setBounceDistance(50)
                .setDuration(3000)
                .animate();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);

                startActivity(intent);

                finish();
            }
        },5000);
    }
}
