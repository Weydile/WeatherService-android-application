package com.weydile.weatherservice.model.api;

import com.weydile.weatherservice.model.pojo.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("city")
    Call<Weather> getWeather(@Query("name") String cityName);

}
