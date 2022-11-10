package com.dicoding.rizkynugraha.cataloguemoviev2;


import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dicoding.rizkynugraha.cataloguemoviev2.model.Movies;

import java.util.ArrayList;

public class DetailActivityMovie extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    ArrayList<Movies> listmovies = new ArrayList<>();

    private ArrayList<Movies> list;
    final String STATE_LIST = "state_list";

    TextView detailTitle,detailRate,detailPopular,detailMonth,detailDesc;
    ImageView detailPhoto,detailPhotoCover;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);


        detailTitle = findViewById(R.id.detail_judul);
        detailPhoto = findViewById(R.id.detail_photo);
        detailRate = findViewById(R.id.detail_rate);
        detailPopular = findViewById(R.id.detail_popular);
        detailMonth = findViewById(R.id.detail_month);
        detailDesc = findViewById(R.id.detail_desc);
        detailPhotoCover = findViewById(R.id.detail_sampul);

        progressBar = findViewById(R.id.progressDetailMovie);

        progressBar.setVisibility(View.VISIBLE);


        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {

                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        String vote_average = Double.toString(movies.getVote_average());
                        String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();
                        String url_image2 = "https://image.tmdb.org/t/p/w500" + movies.getBanner();

                        detailTitle.setText(movies.getTitle());
                        Glide.with(DetailActivityMovie.this)
                                .load(url_image)
                                .placeholder(R.color.birumuda)
                                .dontAnimate()
                                .into(detailPhoto);
                        detailRate.setText(vote_average);
                        detailPopular.setText(movies.getVote_count());
                        detailMonth.setText(movies.getRelease_date());
                        detailDesc.setText(movies.getOverview());
                        Glide.with(DetailActivityMovie.this)
                                .load(url_image2)
                                .placeholder(R.color.birumuda)
                                .dontAnimate()
                                .into(detailPhotoCover);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        list = savedInstanceState.getParcelableArrayList(EXTRA_MOVIE);
        if (list == null){
            Toast.makeText(this, "onRestoreInstanceState:\n" + "NO state Saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"onRestoreInstanceState:\n" + "saved state" + list, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_MOVIE, list);
        Toast.makeText(this,"onSaveRestoreInstance: saved",Toast.LENGTH_SHORT).show();
    }
}
