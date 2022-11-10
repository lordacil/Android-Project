package com.rizkynugraha.sosmedahoy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

    Handler(mainLooper).postDelayed({
        startActivity(Intent(this, LoginActivity::class.java))
    },3000)
    }
}