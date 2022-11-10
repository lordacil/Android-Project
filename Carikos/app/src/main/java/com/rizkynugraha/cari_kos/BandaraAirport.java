package com.rizkynugraha.cari_kos;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BandaraAirport extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterBandara adapter;
    private List<Bandara> transDestination;
    InterfaceApi apiInterface;
    AdapterBandara.RecyclerViewClickListener listener;
    ProgressBar progressBar;
    TextView txtAdmin,textoperator;
    String username;
    SharedPreferences sharedpreferences;

    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bandara_airport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        listener = new AdapterBandara.RecyclerViewClickListener() {

            @Override
            public void onRowClick (View view,final int position){

                Intent intent = new Intent(BandaraAirport.this, EditBandara.class);
                intent.putExtra("id", transDestination.get(position).getId());
                intent.putExtra("id_destination", transDestination.get(position).getId_destination());
                intent.putExtra("name", transDestination.get(position).getNameBandara());
                intent.putExtra("iso", transDestination.get(position).getIso());
                startActivity(intent);

            }

            public void onLoveClick(View view, int position) {

            }
        } ;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BandaraAirport.this, EditBandara.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Desti...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

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
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_rute:
                Intent a = new Intent(BandaraAirport.this, DestinationActivity.class);
                startActivity(a);
                break;
            case R.id.nav_trans:
                Intent b = new Intent(BandaraAirport.this, Kendaraan.class);
                startActivity(b);
                break;
            case R.id.nav_dest:
                Intent d = new Intent(BandaraAirport.this, Kota.class);
                startActivity(d);
                break;
            case R.id.nav_airport:
                Intent f= new Intent(BandaraAirport.this,BandaraActivity.class);
                startActivity(f);
                break;
            case R.id.nav_user:
                Intent e = new Intent(BandaraAirport.this, DestinationActivity.class);
                startActivity(e);
                break;
            case R.id.nav_logot:
                logout();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void getBandara(){

        Call<List<Bandara>> call = apiInterface.getBandara();
        call.enqueue(new Callback<List<Bandara>>() {
            @Override
            public void onResponse(Call<List<Bandara>> call, Response<List<Bandara>> response) {
                progressBar.setVisibility(View.GONE);
                transDestination = response.body();
                Log.i(BandaraActivity.class.getSimpleName(), response.body().toString());
                adapter = new AdapterBandara(transDestination, BandaraAirport.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Bandara>> call, Throwable t) {
                Toast.makeText(BandaraAirport.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBandara();
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

        Intent intent = new Intent(BandaraAirport.this, Login.class);
        finish();
        startActivity(intent);
    }
}
