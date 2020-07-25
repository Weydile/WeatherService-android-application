package com.weydile.weatherservice.model;

import com.weydile.weatherservice.App;
import com.weydile.weatherservice.model.pojo.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherModel {

    public void GetWeather(Callback<Weather> callback, String cityName){
        App.getWeatherApi().getWeather(cityName).enqueue(callback);
    }

}
