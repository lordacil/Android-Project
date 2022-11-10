package ujikom.rizkynugraha.com.tiket_kuy;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.easyandroidanimations.library.BounceAnimation;



public class Splash extends AppCompatActivity {

    private LinearLayout lv_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        //menginisialisasi variable lv_loading sebagai view lv_loading
        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);

        //pembuatan animasi Bounce (atas bawahh)
        new BounceAnimation(lv_loading)
                .setBounceDistance(50)
                .setDuration(3000)
                .animate();

        //membuat sebuah proses yang ter delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //mendefenisikan Intent activity
                Intent i = new Intent(Splash.this,Login.class);
                startActivity(i);

                //finish berguna untuk mengakhiri activity
                //disini saya menggunakan finish,supaya ketika menekan tombol back
                //tidak kembali pada activity SplashScreen nya
                finish();
            }
            //disini maksud 3000 nya itu adalah lama screen ini terdelay 3 detik,dalam satuan mili second
        }, 5000);
    }
}
