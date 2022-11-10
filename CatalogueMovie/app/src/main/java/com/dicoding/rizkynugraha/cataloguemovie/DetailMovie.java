package com.dicoding.rizkynugraha.cataloguemovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailMovie extends AppCompatActivity {

    ImageView img_photo,img_sampul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Intent intent = getIntent();
        Movie movie = intent.getParcelableExtra("DATA");

        String judul = movie.getJudul();
        String rate = movie.getRate();
        String popular = movie.getPopular();
        String year = movie.getYear();
        String month = movie.getMonth();
        String desc = movie.getDesc();
        int photo = movie.getPhoto();
        int sampul = movie.getPhoto_sampul();

        TextView txt_judul = findViewById(R.id.txt_judul);
        txt_judul.setText(judul+" ("+year+") ");

        TextView txt_rate = findViewById(R.id.txt_rate);
        txt_rate.setText(rate);

        TextView txt_popular = findViewById(R.id.txt_popularity);
        txt_popular.setText(popular);

        TextView txt_month = findViewById(R.id.txt_month);
        txt_month.setText(month+","+year);

        TextView txt_desc = findViewById(R.id.txt_desc);
        txt_desc.setText(desc);

        ImageView img_photo = findViewById(R.id.img_photo);
        img_photo.setImageResource(photo);

        ImageView img_sampul = findViewById(R.id.img_sampul);
        img_sampul.setImageResource(sampul);
    }
}
