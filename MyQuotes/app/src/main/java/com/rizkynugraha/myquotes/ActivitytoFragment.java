package com.rizkynugraha.myquotes;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

public class ActivitytoFragment extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(android.R.id.content,new RvFragment()).commit();
        }
    }
}
