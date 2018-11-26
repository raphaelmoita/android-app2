package com.momo.app2.weather;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherClient {

    private static final String TAG = WeatherClient.class.getSimpleName();
    private static String OWM_PPID = "6ce82fb53701d3952b7d5d5239c7fcff";
    private static String OWM_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static String CITY = "Krakow";

    private static String SEARCH_OWM_URL = OWM_BASE_URL + "weather?q=" + CITY + "&APPID=" + OWM_PPID + "&units=metric";

    public WeatherInfo getWeatherInfo() {

        HttpURLConnection urlConnection = null;
        WeatherInfo response = null;

        try {
            URL url = new URL(SEARCH_OWM_URL);

            urlConnection = (HttpURLConnection) url.openConnection();

            final InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(in, WeatherInfo.class);
            Log.i(TAG, response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return response;
    }
}