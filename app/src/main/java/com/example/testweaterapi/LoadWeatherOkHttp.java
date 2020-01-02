package com.example.testweaterapi;

import android.util.Log;

import com.example.testweaterapi.WeatherApi.WeatherApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadWeatherOkHttp {

    String urlGetWeather = "http://api.openweathermap.org/data/2.5/weather?q=Moscow,ru&appid=e83d0265c9865659af525e50e89b8edd";


    static WeatherApi getGSONData( String city) {
//        WeatherApi weatherApi = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",ru&appid=e83d0265c9865659af525e50e89b8edd");

            OkHttpClient okHttpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String rwData = response.body().toString();


            Log.i("rwData", rwData.toString());
//            JSONObject jsonObject = new JSONObject(rwData.toString());

            Gson gson = new Gson();
//            WeatherApi weatherApi = gson.fromJson(rwData.toString(), WeatherApi.class);
            //use toJson???
            WeatherApi weatherApi = gson.fromJson(rwData, WeatherApi.class);



//            return jsonObject;
            return weatherApi;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
