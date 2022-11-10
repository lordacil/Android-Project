package com.dicoding.rizkynugraha.retrofitapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory
                .create()).build();
    }

}
