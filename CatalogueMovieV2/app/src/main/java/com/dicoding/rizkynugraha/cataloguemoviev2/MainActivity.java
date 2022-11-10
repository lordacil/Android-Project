package com.dicoding.rizkynugraha.cataloguemoviev2;


import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.dicoding.rizkynugraha.cataloguemoviev2.mFragments.FragmentMovie;
import com.dicoding.rizkynugraha.cataloguemoviev2.mFragments.FragmentTvShow;


public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener{

    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Catalogue Movie");


        bottomNavigation = findViewById(R.id.myBottomNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(this);
        this.createNavItems();
    }


    private void createNavItems() {
        AHBottomNavigationItem movieItem = new AHBottomNavigationItem(getResources().getString(R.string.movie),R.drawable.ic_movie_24dp);
        AHBottomNavigationItem tvItem = new AHBottomNavigationItem(getResources().getString(R.string.tvshow), R.drawable.ic_tv_24dp);

        bottomNavigation.addItem(movieItem);
        bottomNavigation.addItem(tvItem);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#2c3e50"));
        bottomNavigation.setAccentColor(Color.parseColor("#63C9FF"));
        bottomNavigation.setInactiveColor(Color.parseColor("#fefefe"));
        bottomNavigation.setCurrentItem(0);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {
        if (position==0){
            setTitle(getResources().getString(R.string.movie));
            FragmentMovie fragmentMovie = new FragmentMovie();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,fragmentMovie,fragmentMovie.getClass().getSimpleName()).commit();
        }else if(position==1){
            setTitle(getResources().getString(R.string.tvshow));
            FragmentTvShow fragmentTv= new FragmentTvShow();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_id,fragmentTv,fragmentTv.getClass().getSimpleName()).commit();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }



    }

