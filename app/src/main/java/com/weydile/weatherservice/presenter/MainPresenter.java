package com.weydile.weatherservice.presenter;

import com.weydile.weatherservice.model.CityModel;
import com.weydile.weatherservice.model.WeatherModel;
import com.weydile.weatherservice.model.database.entity.City;
import com.weydile.weatherservice.model.pojo.Weather;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private Activity activity;
    private CityModel cityModel;
    private WeatherModel weatherModel;
    private ArrayList<City> cities;

    public MainPresenter() {
        cityModel = new CityModel();
        weatherModel = new WeatherModel();
    }

    public void attachActivity(Activity activity) {
        this.activity = activity;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void addCity() {
        activity.showProgress();
        String cityName = activity.getCityName();
        weatherModel.GetWeather(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                activity.hideProgress();
                if (response.code() == 200){
                    cityModel.insert(new City(cityName)
                            , new DisposableCompletableObserver() {
                                @Override
                                public void onComplete() {
                                    cities.add(new City(cityName));
                                    activity.update();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }
                            });
                }else{
                    activity.cityNotFound();
                }
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                activity.hideProgress();
                t.printStackTrace();
            }
        }, cityName);
    }

    public void updateData(){
        activity.showProgress();
        cityModel.getAll(new DisposableSingleObserver<List<City>>() {
            @Override
            public void onSuccess(List<City> receivedCities) {
                cities.clear();
                cities.addAll(receivedCities);
                activity.hideProgress();
                activity.update();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteCity(City city) {
        activity.showProgress();
        cityModel.delete(city, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                activity.hideProgress();
                cities.remove(city);
                activity.update();
            }

            @Override
            public void onError(Throwable e) {
                activity.hideProgress();
                e.printStackTrace();
            }
        });
    }

    public interface Activity {
        String getCityName();

        void cityNotFound();

        void showProgress();

        void hideProgress();

        void update();
    }

}
