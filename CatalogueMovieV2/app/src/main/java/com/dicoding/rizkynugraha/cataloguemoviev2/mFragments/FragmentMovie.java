package com.dicoding.rizkynugraha.cataloguemoviev2.mFragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dicoding.rizkynugraha.cataloguemoviev2.model.Movies;
import com.dicoding.rizkynugraha.cataloguemoviev2.R;
import com.dicoding.rizkynugraha.cataloguemoviev2.mRecycler.MyAdapterMovie;
import com.dicoding.rizkynugraha.cataloguemoviev2.viewModel.MoviesViewModel;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMovie extends Fragment{
    MyAdapterMovie adapterMovie;
    private ProgressBar progressBar;
    AnimatedRecyclerView recyclerView;
    MoviesViewModel moviesViewModel;

    public FragmentMovie(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_movie,container,false);
        recyclerView = view.findViewById(R.id.movieRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterMovie = new MyAdapterMovie();
        recyclerView.setAdapter(adapterMovie);



        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.getMovies().observe(this,getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);

     return view;
    }


    private Observer<ArrayList<Movies>> getMovie = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies){
            if (movies!=null){
                adapterMovie.setData(movies);

            }
            showLoading(false);
        }
    };

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

}
