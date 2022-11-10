package ujikom.rizkynugraha.com.tiket_kuy;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapters adapter;
    private List<Transportasi> transList;
    InterfaceApi apiInterface;
    Adapters.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans);

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final CardView cardView = (CardView) findViewById(R.id.cv);
        listener = new Adapters.RecyclerViewClickListener() {

            @Override
            public void onRowClick (View view,final int position){

                cardView.setCardBackgroundColor(Color.parseColor("#D4D4D4"));
                Intent intent = new Intent(TransActivity.this, EditActivity.class);
                intent.putExtra("id_transportasi", transList.get(position).getId_transportasi());
                intent.putExtra("kode", transList.get(position).getKode());
                intent.putExtra("jumlah_kursi", transList.get(position).getJumlah_kursi());
                intent.putExtra("nama_type", transList.get(position).getNama_type());
                intent.putExtra("keterangan", transList.get(position).getKeterangan());
                intent.putExtra("id_type_transportasi", transList.get(position).getId_type_transportasi());
                intent.putExtra("picture", transList.get(position).getPicture());
                startActivity(intent);

            }

            public void onLoveClick(View view, int position) {

            }
        } ;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TransActivity.this, EditActivity.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Trans...");
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

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getTrans(){

        Call<List<Transportasi>> call = apiInterface.getTrans();
        call.enqueue(new Callback<List<Transportasi>>() {
            @Override
            public void onResponse(Call<List<Transportasi>> call, Response<List<Transportasi>> response) {
                progressBar.setVisibility(View.GONE);
                transList = response.body();
                Log.i(TransActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapters(transList, TransActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Transportasi>> call, Throwable t) {
                Toast.makeText(TransActivity.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTrans();
    }

}
