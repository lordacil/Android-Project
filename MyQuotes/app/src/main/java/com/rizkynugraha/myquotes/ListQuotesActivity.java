package com.rizkynugraha.myquotes;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListQuotesActivity extends AppCompatActivity {
    private ListView listView;
    private ProgressBar progressBar;
    private static final String TAG = ListQuotesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quotes);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("List of Quotes");
        }

        listView = findViewById(R.id.listQuotes);
        progressBar = findViewById(R.id.progressBar);
        getListQuotes();
    }

    private void getListQuotes(){
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://programming-quotes-api.herokuapp.com/quotes/page/1";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // jika koneksi berhasil
                progressBar.setVisibility(View.INVISIBLE);

                // Parsing JSON
                ArrayList<String> listQuote = new ArrayList<>();

                String result = new String(responseBody);
                Log.d(TAG,result);
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String quote = jsonObject.getString("en");
                        String author = jsonObject.getString("author");
                        listQuote.add("\n"+quote+ "\n - "+author+"\n");
                    }

                    ArrayAdapter adapter = new ArrayAdapter<>(ListQuotesActivity.this, android.R.layout.simple_list_item_1, listQuote);
                    listView.setAdapter(adapter);
                }catch (Exception e){
                    Toast.makeText(ListQuotesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // jika koneksi gagal
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode){
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbiden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(ListQuotesActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}