package ujikom.rizkynugraha.com.tiket_kuy;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



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

    TextView txt_profile_name;
    TextView textViewDrawer;
    String username;
    String picture;
    SharedPreferences sharedpreferences;
    Intent intent;
    ImageView glideimage;
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        glideimage = (ImageView) findViewById(R.id.imageDrawer);

        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView textViewDrawer = (TextView)hView.findViewById(R.id.textViewDrawer);
        ImageView imageView = (ImageView) findViewById(R.id.imageDrawer);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        txt_profile_name.setText(username);
        textViewDrawer.setText(username);
//        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
//        txt_profile_name.setText(Objects.requireNonNull(getIntent().getExtras().get("name")).toString());
//        textViewDrawer = (TextView) findViewById(R.id.textViewDrawer);
//        textViewDrawer.setText(Objects.requireNonNull(getIntent().getExtras().get("name")).toString());

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Eve  nt
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.info1));
        models.add(new Model(R.drawable.info5));
        models.add(new Model(R.drawable.info2));
        models.add(new Model(R.drawable.info3));
        models.add(new Model(R.drawable.info4));

        models2 = new ArrayList<>();
        models2.add(new Model2(R.drawable.kereta,"Jalan Baru Kereta dan Keindahan Jalur Tepi Sungai Serayu","Gunung dan sungai di wilayah Banyumas memang menyajikan pemandangan yang amat memesona bagi penumpang kereta api."));
        models2.add(new Model2(R.drawable.pesawat,"Berkat Kecerdasan Buatan, Pesawat Bakal Bisa Terbang Tanpa Pilot","Dengan teknologi tersebut, produsen pesawat yang berbasis di Prancis itu berencana untuk mengganti satu pilot dengan komputer, hingga "));

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
                {getResources().getColor(R.color.color1),
                        getResources().getColor(R.color.color2),
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

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
                        Intent intent = new Intent(MenuActivity.this,webview.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1)
                    {

                        Intent intent = new Intent(MenuActivity.this,webview.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2)
                    {

                        Intent intent = new Intent(MenuActivity.this,CaraPesan.class);
                        startActivity(intent);
                    }
                    else if(finalI == 3)
                    {
                        //cardView.setCardBackgroundColor(Color.parseColor("#D4D4D4"));
                        Intent intent = new Intent(MenuActivity.this,webview_order.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MenuActivity.this, "Wrong click :*", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            // Handle the camera action
            logout();

        }else if(id == R.id.nav_profile) {
            Intent d = new Intent(MenuActivity.this, ProfileUser.class);
            startActivity(d);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout(){
       /* FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent); */
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(Login.session_status, false);
        editor.putString(TAG_USERNAME, null);
        editor.commit();

        Intent intent = new Intent(MenuActivity.this, Login.class);
        finish();
        startActivity(intent);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MenuActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}
