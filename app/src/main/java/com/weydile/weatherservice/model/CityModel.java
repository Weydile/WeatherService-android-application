package com.weydile.weatherservice.model;

import com.weydile.weatherservice.App;
import com.weydile.weatherservice.model.database.dao.CityDao;
import com.weydile.weatherservice.model.database.entity.City;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CityModel {

    private CityDao dao;

    public CityModel() {
        dao = App.getDatabase().cityDao();
    }

    public void insert(City city, DisposableCompletableObserver callback) {
        dao.insert(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public void getAll(DisposableSingleObserver<List<City>> callback) {
        dao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

    public void delete(City city, DisposableCompletableObserver callback){
        dao.delete(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(callback);
    }

}
