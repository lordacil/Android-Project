package com.rizkynugraha.myquotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class RvFragment extends Fragment {

    MyAdapterUser adapterMovie;
    private ProgressBar progressBar;
    AnimatedRecyclerView recyclerView;
    UserViewModel moviesViewModel;

    public RvFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_rv,container,false);
        recyclerView = view.findViewById(R.id.movieRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterMovie = new MyAdapterUser();
        recyclerView.setAdapter(adapterMovie);



        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        moviesViewModel.getMovies().observe(getViewLifecycleOwner(),getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }


    private Observer<ArrayList<Users>> getMovie = new Observer<ArrayList<Users>>() {
        @Override
        public void onChanged(ArrayList<Users> movies){
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