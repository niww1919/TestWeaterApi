package com.example.testweaterapi;

import android.content.Context;
import android.util.Log;

import com.example.testweaterapi.WeatherApi.WeatherApi;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadWeather {

    String urlGetWeather = "http://api.openweathermap.org/data/2.5/weather?q=Moscow,ru&appid=e83d0265c9865659af525e50e89b8edd";


    static JSONObject getJSONData( String city) {
//        WeatherApi weatherApi = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+",ru&appid=e83d0265c9865659af525e50e89b8edd");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder rwData = new StringBuilder(1024);
            String temp;
            while ((temp = reader.readLine()) != null) {
                rwData.append(temp).append("\n");
            }
            reader.close();

            Log.i("rwData", rwData.toString());
            JSONObject jsonObject = new JSONObject(rwData.toString());

//            Gson gson = new Gson();
//            final WeatherApi weatherApi = gson.fromJson(rwData.toString(), WeatherApi.class);



//            return jsonObject;
            return jsonObject;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}
