package com.example.testweaterapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testweaterapi.WeatherApi.WeatherApi;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        loadWeatherData("Moscow" );
//        loadWeatherData("Saint Petersburg" );
//        loadWeatherDataGson("Saint Petersburg" );
        loadWeatherDataOkHttp("Omsk");
//        loadWeatherDataRetrofit("Omsk");

    }

    private void loadWeatherData(final String city) {
        new Thread() {
            @Override
            public void run() {
                final JSONObject json = LoadWeather.getJSONData(city);
                Log.i("Json1", json.toString());
                if (json == null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Null", Toast.LENGTH_LONG).show();
                        }
                    });

                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            readWeather(json);
                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        }.start();

    }
    private void loadWeatherDataGson(final String city) {
        new Thread() {
            @Override
            public void run() {
                final WeatherApi weatherApi = LoadWeatherGson.getGSONData(city);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            readWeather(json);
                            readWeatherGson(weatherApi);
                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }.start();

    }

    private void loadWeatherDataOkHttp(final String city) {
        new Thread() {
            @Override
            public void run() {
                final WeatherApi weatherApi = LoadWeatherOkHttp.getDataByOkHttp(city);
//                LoadWeatherRetrofit.getDataByRetrofit(city);//fixme test load data in log "retrofit"

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            readWeather(json);
                            readWeatherGson(weatherApi);
                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }.start();

    }
    private void loadWeatherDataRetrofit(final String city) {
        new Thread() {
            @Override
            public void run() {
                LoadWeatherRetrofit.getDataByRetrofit(city);//fixme test load data in log "retrofit"

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//                            readWeather(json);
//                            readWeatherGson(weatherApi);
                            Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        }.start();

    }

    private void readWeather(JSONObject jsonObject) {
        Log.i("Json2", jsonObject.toString());
        try {
            ((TextView)findViewById(R.id.city)).setText(jsonObject.getString("name"));
            JSONObject main = jsonObject.getJSONObject("main");

            ((TextView)findViewById(R.id.weather)).setText(String.format( "%.2f",(main.getDouble("temp") - 273))+ " C");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void readWeatherGson(WeatherApi weatherApi) {
        Log.i("Json2", weatherApi.toString());

            ((TextView)findViewById(R.id.city)).setText(weatherApi.getCity().getName());

//            ((TextView)findViewById(R.id.weather)).setText(weatherApi.().getTemp().toString());

    }


}
