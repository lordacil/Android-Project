package com.dicoding.rizkynugraha.cataloguemoviev2.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.dicoding.rizkynugraha.cataloguemoviev2.model.Movies;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesViewModel extends ViewModel{
    private static final String API_KEY ="b4f325afcde492a3885b7545e32c9386";
    private MutableLiveData<ArrayList<Movies>> listMovies = new MutableLiveData<>();

    public void setMovies(final String movies){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movies> listItem = new ArrayList<>();

        String url ="https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        Movies moviesItems = new Movies(weather);
                        listItem.add(moviesItems);
                    }
                    listMovies.postValue(listItem);
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
    public LiveData<ArrayList<Movies>> getMovies(){
        return listMovies;
    }
}
