package com.rizkynugraha.cari_kos;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rizkynugraha.cari_kos.models.Model;
import com.rizkynugraha.cari_kos.models.Model2;
import com.rizkynugraha.cari_kos.util.ViewPagerAdapter;
import com.rizkynugraha.cari_kos.util.ViewPagerAdapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView txt_profile_name;
    String username;
    SharedPreferences sharedpreferences;
    GridLayout mainGrid;
    ViewPager viewPager;
    ViewPager viewPager2;
    ViewPagerAdapter viewpageradapter;
    ViewPagerAdapter2 viewPagerAdapter2;
    List<Model> models;
    List<Model2> models2;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    Intent intent;
    ImageView glideimage;
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
        txt_profile_name.setText(username);
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Eve  nt
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);

        models = new ArrayList<>();
        models.add(new Model(R.mipmap.foto1));
        models.add(new Model(R.mipmap.foto2));
        models.add(new Model(R.mipmap.foto4));
        models.add(new Model(R.mipmap.foto3));
        models.add(new Model(R.mipmap.foto5));

        models2 = new ArrayList<>();
        models2.add(new Model2(R.mipmap.diskon,"Diskon Akhir Tahun !","Mumpung lagi promo nih CariKos lagi Diskon 50% dalam rangka Akhir Tahun, yukk buruan !"));
        models2.add(new Model2(R.mipmap.purwokerto,"Kota Purwokerto","Kota Purwokerto adalah ibu Kota Kabupaten Banyumas, Jawa Tengah, di Purwokerto banyak sekali Mahasiswa dari luar jawa..."));

        viewpageradapter = new ViewPagerAdapter(models,this);
        viewPagerAdapter2 = new ViewPagerAdapter2(models2,this);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(viewpageradapter);
        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(viewPagerAdapter2);

        dotscount = viewpageradapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        Integer[] colors_temp =
                {getResources().getColor(R.color.petterriver),
                        getResources().getColor(R.color.belizhole),
                };

        colors = colors_temp;

        viewPager2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(finalI == 0) // buka activity 1
                    {
                        Intent intent = new Intent(MainActivity.this,FragtoActivityGirl.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1)
                    {

                        Intent intent = new Intent(MainActivity.this,FragtoActivityBoy.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2)
                    {

                        Intent intent = new Intent(MainActivity.this,HowtobuyActivity.class);
                        startActivity(intent);
                    }
                    else if(finalI == 3)
                    {
                        //cardView.setCardBackgroundColor(Color.parseColor("#D4D4D4"));
                        Intent intent = new Intent(MainActivity.this,DonationActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong click :*", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.about:
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_about,null);
                Button btn_back = (Button)mView.findViewById(R.id.kembali);

                alert.setView(mView);

                final AlertDialog alertDialog = alert.create();
                alertDialog.setCanceledOnTouchOutside(false);

                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }



    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    } else if(viewPager.getCurrentItem() == 3){
                        viewPager.setCurrentItem(4);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}
