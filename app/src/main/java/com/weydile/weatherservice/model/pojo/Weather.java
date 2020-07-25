package com.weydile.weatherservice.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("coordinates")
    @Expose
    private Coordinates coordinates;
    @SerializedName("main_params")
    @Expose
    private MainParams mainParams;
    @SerializedName("weather_description")
    @Expose
    private WeatherDescription weatherDescription;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("cod")
    @Expose
    private Integer code;


    public String getCityName() {
        return cityName;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public MainParams getMainParams() {
        return mainParams;
    }

    public WeatherDescription getWeatherDescription() {
        return weatherDescription;
    }

    public Wind getWind() {
        return wind;
    }

    public Integer getCode() {
        return code;
    }
}
