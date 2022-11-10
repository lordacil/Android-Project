package com.rizkynugraha.cari_kos;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragtoActivityBoy extends FragmentActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(android.R.id.content,new menuBoyFragment()).commit();
        }
    }
}
