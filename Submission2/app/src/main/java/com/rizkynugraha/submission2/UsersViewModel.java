package com.rizkynugraha.submission2;

import android.util.Log;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.rizkynugraha.submission2.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

public class UsersViewModel extends ViewModel{
    private static final String API_KEY ="sidiqpermana";
    private MutableLiveData<ArrayList<User>> listMovies = new MutableLiveData<>();

    public void setMovies(final String movies){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<User> listItem = new ArrayList<>();

        String url ="https://api.github.com/users/"+API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String result = new String(responseBody);
                try {
                    JSONObject responsObject = new JSONObject(result);

                    String quote = responsObject.getString("en");
                    String author = responsObject.getString("author");

                    tvQuote.setText(quote);
                    tvAuthor.setText(author);
                }catch (Exception e){
                    Log.d("OnFailure", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Exception", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<User>> getMovies(){
        return listMovies;
    }
}
