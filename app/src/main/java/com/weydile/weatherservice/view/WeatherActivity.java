package com.weydile.weatherservice.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.weydile.weatherservice.R;
import com.weydile.weatherservice.model.pojo.Weather;
import com.weydile.weatherservice.presenter.WeatherPresenter;

public class WeatherActivity extends AppCompatActivity implements WeatherPresenter.Activity {

    private WeatherPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        init();
        presenter.getWeather(getIntent().getExtras().getString("cityName"));
    }

    private void init() {
        presenter = new WeatherPresenter();
        presenter.attachActivity(this);
    }

    @Override
    public void showWeather(Weather weather) {
        findViewById(R.id.weather_layout).setVisibility(View.VISIBLE);
        TextView textView = findViewById(R.id.city_name);
        textView.setText(weather.getCityName());

        textView = findViewById(R.id.average_temperature);
        textView.setText(String.valueOf(weather.getMainParams().getAverageTemperature()));

        textView = findViewById(R.id.weather_description);
        textView.setText(weather.getWeatherDescription().getDescription());

        textView = findViewById(R.id.wind_direction);
        switch (weather.getWind().getDeg() /45){
            case 0:
            case 8:
                textView.setText(R.string.north);
                break;
            case 1:
                textView.setText(R.string.northeast);
                break;
            case 2:
                textView.setText(R.string.east);
                break;
            case 3:
                textView.setText(R.string.southeast);
                break;
            case 4:
                textView.setText(R.string.south);
                break;
            case 5:
                textView.setText(R.string.southwest);
                break;
            case 6:
                textView.setText(R.string.west);
                break;
            case 7:
                textView.setText(R.string.northwest);
                break;
        }

        textView = findViewById(R.id.wind_speed);
        textView.setText(String.valueOf(weather.getWind().getSpeed()));

        textView = findViewById(R.id.pressure_value);
        textView.setText(String.valueOf(weather.getMainParams().getPressure()));

        textView = findViewById(R.id.humidity_value);
        textView.setText(String.valueOf(weather.getMainParams().getHumidity()));
    }

    @Override
    public void showError(int code) {
        findViewById(R.id.error_layout).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.error_code_value)).setText(String.valueOf(code));
        switch (code){
            case 404:
                ((TextView)findViewById(R.id.advice)).setText(R.string.advice_404);
                break;
        }
    }

    @Override
    public void showProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }
}