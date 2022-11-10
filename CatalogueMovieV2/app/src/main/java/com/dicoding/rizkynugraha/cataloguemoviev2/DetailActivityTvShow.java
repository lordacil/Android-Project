package com.dicoding.rizkynugraha.cataloguemoviev2;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.rizkynugraha.cataloguemoviev2.model.TvShow;


public class DetailActivityTvShow extends AppCompatActivity {
    public static final String EXTRA_TV_SHOW = "extra_movie";

    private TextView detailTitle,detailRate,detailPopular,detailMonth,detailDesc;
    private ImageView detailPhoto,detailPhotoCover;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        detailTitle = findViewById(R.id.detail_judul);
        detailPhoto = findViewById(R.id.detail_photo);
        detailRate = findViewById(R.id.detail_rate);
        detailPopular = findViewById(R.id.detail_popular);
        detailMonth = findViewById(R.id.detail_month);
        detailDesc = findViewById(R.id.detail_desc);
        detailPhotoCover = findViewById(R.id.detail_sampul);

        progressBar = findViewById(R.id.progressDetailTv);

        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    Log.d("ERROR", e.getMessage());
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV_SHOW);

                        String vote_average = Double.toString(tvShow.getVote_average());
                        String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();
                        String url_image2 = "https://image.tmdb.org/t/p/w500" + tvShow.getBackdrop_path();

                        detailTitle.setText(tvShow.getName());
                        Glide.with(DetailActivityTvShow.this)
                                .load(url_image)
                                .into(detailPhoto);
                        detailRate.setText(vote_average);
                        detailPopular.setText(tvShow.getVote_count());
                        detailMonth.setText(tvShow.getFirst_air_date());
                        detailDesc.setText(tvShow.getOverview());
                        Glide.with(DetailActivityTvShow.this)
                                .load(url_image2)
                                .into(detailPhotoCover);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }

    }