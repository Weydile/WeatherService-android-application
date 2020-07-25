package com.weydile.weatherservice;

import android.app.Application;

import androidx.room.Room;

import com.weydile.weatherservice.model.api.WeatherApi;
import com.weydile.weatherservice.model.database.AppDatabase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static WeatherApi weatherApi;
    private static AppDatabase database;

    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openweather-service.herokuapp.com/weather/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         weatherApi = retrofit.create(WeatherApi.class);
         database = Room.databaseBuilder(this, AppDatabase.class, "database")
                 .fallbackToDestructiveMigration()
                 .build();
    }

    public static WeatherApi getWeatherApi() {
        return weatherApi;
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
