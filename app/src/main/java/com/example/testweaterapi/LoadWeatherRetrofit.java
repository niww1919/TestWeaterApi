package com.example.testweaterapi;

import android.util.Log;

import com.example.testweaterapi.WeatherApi.WeatherApi;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class LoadWeatherRetrofit {

    public static final String BASE_URL = "http://api.openweathermap.org/";
    String urlGetWeather = BASE_URL + "data/2.5/weather?q=Moscow,ru&appid=e83d0265c9865659af525e50e89b8edd";


    static void getDataByRetrofit(String city) {

//        WeatherApi weatherApi = null;
        try {


            URL url = new URL(BASE_URL + "data/2.5/weather?q=" +city+",ru&appid=e83d0265c9865659af525e50e89b8edd");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
//            WeatherApi weatherApi = retrofit.create(WeatherApi.class);
            ApiService apiService = retrofit.create(ApiService.class);

            Call<WeatherApi> apiCall = apiService.getCity();

            apiCall.enqueue(new Callback<WeatherApi>() {
                @Override
                public void onResponse(Call<WeatherApi> call, Response<WeatherApi> response) {
                    WeatherApi weatherApi = response.body(); //fixme
                    Log.i("retrofit", weatherApi.getCity().getName());
                }

                @Override
                public void onFailure(Call<WeatherApi> call, Throwable t) {

                }
            });


//            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    interface ApiService {
        @GET("data/2.5/weather?q=Saint Petersburg,ru&appid=e83d0265c9865659af525e50e89b8edd")
        Call<WeatherApi> getCity();

//        Call<WeatherApi> getWeather;
    }
}
