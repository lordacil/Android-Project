package com.dicoding.rizkynugraha.retrofitapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dicoding.rizkynugraha.retrofitapi.network.RestApi;
import com.dicoding.rizkynugraha.retrofitapi.network.Constant;
import com.dicoding.rizkynugraha.retrofitapi.network.NetworkUtils;
import com.dicoding.rizkynugraha.retrofitapi.network.pojo.Movie;
import com.dicoding.rizkynugraha.retrofitapi.models.ResponseMovie;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterMovie.MClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.llcontainer)
    LinearLayout llcontainer;

    @BindView(R.id.ivNodata)
    ImageView ivNodata;

    private AdapterMovie adapterMovie;
    private ResponseMovie responseMovie;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        swipeRefreshLayout();
        SearchMovie();
    }

    private void swipeRefreshLayout(){
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.white), getResources().getColor(android.R.color.white));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                ShowMovie();
            }
        });
    }

    private void SearchMovie(){
        etSerch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ShowMovie();
            }
        });
    }

    @Override
    public void onRefresh(){
        ShowMovie();
    }

    private void ShowMovie(){
        configRecycleView();
        RestApi restApi = NetworkUtils.getRetrofit().create(RestApi.class);
        Call<ResponseMovie> call=restApi.getMovie(Constant.API_KEY, Constant.LANGUAGE, etSearch.getText().toString());
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(@NonNull Call<ResponseMovie> call, @NonNull final Response<ResponseMovie> rs) {
                if (rs.isSuccessful()){
                    responseMovie = rs.body();

                    if (rs.body().total_results != 0){
                        for (int i = 0; i < responseMovie.results.size(); i++){
                            Movie movie = responseMovie.results.get(i);
                            adapterMovie.addMovie(movie);
                        }
                        llcontainer.setVisibility(View.VISIBLE);
                        ivNodata.setVisibility(View.GONE);
                    }else{
                        llcontainer.setVisibility(View.GONE);
                        ivNodata.setVisibility(View.VISIBLE);
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("CODEE", String.valueOf(t.getMessage()));
            }
        });
    }

   public void configRecycleView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterMovie = new AdapterMovie(this);
        recyclerView.setAdapter(adapterMovie);
   }

   @Override
    public void onClick(int position){
        movie = adapterMovie.getMenu(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("vote_count", movie.vote_count);
       intent.putExtra("vote_average", movie.vote_average);
       intent.putExtra("title", movie.title);
       intent.putExtra("popularity", movie.popularity);
       intent.putExtra("poster_path", movie.poster_path);
       intent.putExtra("original_language", movie.original_language);
       intent.putExtra("original_title", movie.original_title);
       intent.putExtra("overview", movie.overview);
       intent.putExtra("release_date", movie.release_date);

        startActivity(intent);
    }

}
