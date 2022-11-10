package com.dicoding.rizkynugraha.cataloguemoviev2.mFragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.rizkynugraha.cataloguemoviev2.R;
import com.dicoding.rizkynugraha.cataloguemoviev2.model.TvShow;
import com.dicoding.rizkynugraha.cataloguemoviev2.mRecycler.MyAdapterTvShow;
import com.dicoding.rizkynugraha.cataloguemoviev2.viewModel.TVShowViewModel;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTvShow extends Fragment {
    private MyAdapterTvShow adapterTvShow;
    private ProgressBar progressBar;
    TVShowViewModel tvShowViewModel;

    public FragmentTvShow(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        adapterTvShow = new MyAdapterTvShow();
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        AnimatedRecyclerView recyclerView = view.findViewById(R.id.tvRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapterTvShow);

        progressBar = view.findViewById(R.id.progressBar);

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
        tvShowViewModel.setTvShow("EXTRA_TV_SHOW");

        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShows){
            if (tvShows != null){
                adapterTvShow.setTvData(tvShows);
            }

            showLoading(false);
        }
    };

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
