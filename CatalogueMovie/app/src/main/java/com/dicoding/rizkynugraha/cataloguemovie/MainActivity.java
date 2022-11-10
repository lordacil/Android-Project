package com.dicoding.rizkynugraha.cataloguemovie;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private String[] dataJudul,dataRate,dataPopular,dataYear,dataMonth,dataDesc;
    private TypedArray dataPhoto,dataSampul;
    private ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(MainActivity.this, DetailMovie.class);
                intent.putExtra("DATA", movies.get(i));
                startActivity(intent);
            }
        });
    }

    private  void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataJudul.length; i++) {
            Movie movie = new Movie(Parcel.obtain());
            movie.setPhoto(dataPhoto.getResourceId(i, -1));
            movie.setPhoto_sampul(dataSampul.getResourceId(i,-1));
            movie.setJudul(dataJudul[i]);
            movie.setRate((dataRate[i]));
            movie.setPopular((dataPopular[i]));
            movie.setYear((dataYear[i]));
            movie.setMonth((dataMonth[i]));
            movie.setDesc((dataDesc[i]));
            movies.add(movie);
        }

        adapter.setMovies(movies);
    }

    private void prepare() {
        dataJudul = getResources().getStringArray(R.array.data_judul);
        dataRate = getResources().getStringArray(R.array.data_rate);
        dataPopular = getResources().getStringArray(R.array.data_popular);
        dataYear = getResources().getStringArray(R.array.data_year);
        dataMonth = getResources().getStringArray(R.array.data_month);
        dataDesc = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
        dataSampul = getResources().obtainTypedArray(R.array.data_photo_sampul);
    }
}
