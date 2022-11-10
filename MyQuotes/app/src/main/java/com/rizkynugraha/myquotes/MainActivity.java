package com.rizkynugraha.myquotes;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView tvQuote, tvAuthor;
    private ProgressBar progressBar;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvQuote = findViewById(R.id.tvQuote);
        tvAuthor = findViewById(R.id.tvAuthor);
        progressBar = findViewById(R.id.progressBar);
        getRandomQuote();

        Button btnAllQuote = findViewById(R.id.btnAllQuotes);
        btnAllQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ListQuotesActivity.class));
            }
        });
        Button btnQuotes = findViewById(R.id.quotesRV);
        btnQuotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ActivitytoFragment.class));
            }
        });
    }

    private void getRandomQuote(){
        progressBar.setVisibility(View.VISIBLE);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://programming-quotes-api.herokuapp.com/quotes/random";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // jika koneksi berhasil
                progressBar.setVisibility(View.INVISIBLE);

                String result = new String(responseBody);
                Log.d(TAG, result);
                try {
                    JSONObject responsObject = new JSONObject(result);

                    String quote = responsObject.getString("en");
                    String author = responsObject.getString("author");

                    tvQuote.setText(quote);
                    tvAuthor.setText(author);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage = statusCode + " : " + error.getMessage();
                        break;
                }
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}