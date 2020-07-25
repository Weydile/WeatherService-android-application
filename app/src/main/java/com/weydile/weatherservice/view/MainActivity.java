package com.weydile.weatherservice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.weydile.weatherservice.R;
import com.weydile.weatherservice.adapter.CityAdapter;
import com.weydile.weatherservice.model.database.entity.City;
import com.weydile.weatherservice.presenter.MainPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.Activity, CityAdapter.Activity {

    private MainPresenter presenter;
    private CityAdapter adapter;
    private ArrayList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();
        presenter.updateData();
    }

    private void init() {
        setSupportActionBar(findViewById(R.id.toolbar));

        cities = new ArrayList<>();

        adapter = new CityAdapter(cities, this);

        presenter = new MainPresenter();
        presenter.attachActivity(this);
        presenter.setCities(cities);

        ((RecyclerView)findViewById(R.id.city_list)).setAdapter(adapter);
    }

    private void setListeners() {
        findViewById(R.id.floatingActionButton).setOnClickListener(v -> showInputField());
       ((TextInputLayout) findViewById(R.id.input_city)).setEndIconOnClickListener(v -> {
           hideInputField();
           presenter.addCity();
       });
    }

    private void hideInputField() {
        findViewById(R.id.floatingActionButton).setVisibility(View.VISIBLE);
        findViewById(R.id.input_city).setVisibility(View.GONE);
    }

    private void showInputField() {
        findViewById(R.id.floatingActionButton).setVisibility(View.GONE);
        findViewById(R.id.input_city).setVisibility(View.VISIBLE);
    }

    @Override
    public String getCityName() {
        TextInputEditText cityNameField =  findViewById(R.id.input_city_text);
        String name = cityNameField.getText().toString();
        cityNameField.setText("");
        return name;
    }

    @Override
    public void cityNotFound() {
        Toast.makeText(this, R.string.advice_404, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
    }

    @Override
    public void update() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showCityWeather(String cityName) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
    }

    @Override
    public void deleteCity(City city) {
        presenter.deleteCity(city);
    }
}