package com.rizkynugraha.androidbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.rizkynugraha.androidbasic.databinding.LifecycleActivityBinding

class LifeCycleActivity : AppCompatActivity() {

    // lateinit sebuah variable yang diinisialisasi nanti
    private lateinit var binding: LifecycleActivityBinding
    private var status = "Status: \n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LifecycleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        status+="Status: onCreate \n"
        binding.tvStatus.text = status

//        val textView = findViewById<TextView>(R.id.tvStatus)
//        textView.text = "Bukan Status"
    }

    override fun onStart() {
        super.onStart()
        setStatus("onStart")
    }

    override fun onResume() {
        super.onResume()
        setStatus("onResume")
    }

    override fun onPause() {
        super.onPause()
        setStatus("onPause")
    }

    override fun onStop() {
        super.onStop()
        setStatus("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        setStatus("onDestroy")
    }

    fun setStatus(mStatus: String){
        status+="Status: $mStatus \n"
        binding.tvStatus.text = status
    }
}