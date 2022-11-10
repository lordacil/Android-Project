package ujikom.rizkynugraha.com.tiket_kuy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class Admin extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar=null;
    TextView txtAdmin,textoperator;
    String username;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        Button btn_webview = (Button) findViewById(R.id.btn_webview);
        Button btn_webview_dua = (Button) findViewById(R.id.btn_webview_dua);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView txtAdmin = (TextView)hView.findViewById(R.id.txtAdmin);
        TextView textoperator = (TextView) findViewById(R.id.textoperator);
        sharedpreferences = getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
        username = getIntent().getStringExtra(TAG_USERNAME);
        txtAdmin.setText(username);
        textoperator.setText(username);

        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent= new Intent(Admin.this,webview_admin.class);
                startActivity(intent);
            }
        });

        btn_webview_dua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                Intent intent= new Intent(Admin.this,webview_petugas.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_rute) {
//            // Handle the camera action
//
//        } else if (id == R.id.nav_trans) {
//            Intent intent = new Intent(Admin.this,TransActivity.class);
//            finish();
//            startActivity(intent);
//        } else if (id == R.id.nav_dest) {
//            Intent intent = new Intent(Admin.this,DestinationActivity.class);
//            finish();
//            startActivity(intent);
//        } else if (id == R.id.nav_user) {
//
//        } else if (id == R.id.nav_logot) {
//            logout();
//        } else if (id == R.id.nav_payment) {
//
//        }
        int id=item.getItemId();
        switch (id) {
            case R.id.nav_rute:
                Intent a= new Intent(Admin.this,DestinationActivity.class);
                startActivity(a);
                break;
            case R.id.nav_trans:
                Intent b= new Intent(Admin.this,Kendaraan.class);
                startActivity(b);
                break;
            case R.id.nav_dest:
                Intent d= new Intent(Admin.this,Kota.class);
                startActivity(d);
                break;
            case R.id.nav_airport:
                Intent e= new Intent(Admin.this,BandaraAirport.class);
                startActivity(e);
                break;
            case R.id.nav_jdwl:
                Intent f= new Intent(Admin.this,BandaraAirport.class);
                startActivity(f);
                break;
            case R.id.nav_user:
                Intent g= new Intent(Admin.this,DestinationActivity.class);
                startActivity(g);
                break;
            case R.id.nav_logot:
               logout();
               break;
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

        Intent intent = new Intent(Admin.this, Login.class);
        finish();
        startActivity(intent);
    }
}
