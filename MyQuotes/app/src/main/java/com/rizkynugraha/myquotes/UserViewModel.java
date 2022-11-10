package com.rizkynugraha.myquotes;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cz.msebera.android.httpclient.Header;

public class UserViewModel extends ViewModel {
    private static final String API_KEY ="sidiqpermana";
    private MutableLiveData<ArrayList<Users>> listMovies = new MutableLiveData<>();

    public void setMovies(final String movies){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Users> listItem = new ArrayList<>();

        String url ="https://api.github.com/search/users?q="+API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("items");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        Users moviesItems = new Users(weather);
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
    public LiveData<ArrayList<Users>> getMovies(){
        return listMovies;
    }
}
