package com.rizkynugraha.submission2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mlsdev.animatedrv.AnimatedRecyclerView;
import com.rizkynugraha.submission2.Model.User;

import java.util.ArrayList;


public class UserFragment extends Fragment {
    MyAdapterUsers adapterMovie;
    private ProgressBar progressBar;
    RecyclerView recyclerView;
    UsersViewModel moviesViewModel;

    public UserFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        recyclerView = view.findViewById(R.id.movieRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapterMovie = new MyAdapterUsers();
        recyclerView.setAdapter(adapterMovie);



        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        moviesViewModel.getMovies().observe(this,getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);

        return view;
    }


    private Observer<ArrayList<User>> getMovie = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> movies){
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