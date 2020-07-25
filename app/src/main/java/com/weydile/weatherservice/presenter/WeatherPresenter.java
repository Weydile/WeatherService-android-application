package com.weydile.weatherservice.presenter;

import com.weydile.weatherservice.model.WeatherModel;
import com.weydile.weatherservice.model.pojo.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter {

    private Activity activity;
    private WeatherModel model;

    public WeatherPresenter() {
        model = new WeatherModel();
    }

    public void attachActivity(Activity activity){
        this.activity = activity;
    }

    public void getWeather(String cityName){
        activity.showProgressBar();
        model.GetWeather(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                activity.hideProgressBar();
                if (response.code() == 200) {
                    activity.showWeather(response.body());
                } else {
                    activity.showError(response.code());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                t.printStackTrace();
            }
        }, cityName);
    }

    public interface Activity{
        void showWeather(Weather weather);
        void showError(int code);
        void showProgressBar();
        void hideProgressBar();
    }
}
