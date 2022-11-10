package com.rizkynugraha.androidbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import com.rizkynugraha.androidbasic.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_view)

        binding.apply {
            imageView2.load("anjay")
        }
    }
}