package ujikom.rizkynugraha.com.tiket_kuy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haerul on 15/03/18.
 */

class ApiClient {

    private static final String BASE_URL = "http://forstone.web.id/tiket-kuy/";
    private static Retrofit retrofit;


    static Retrofit getApiClient(){

        if (retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
