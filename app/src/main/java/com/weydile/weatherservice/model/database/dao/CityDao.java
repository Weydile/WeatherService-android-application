package com.weydile.weatherservice.model.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.weydile.weatherservice.model.database.entity.City;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface CityDao {

    @Query("SELECT * FROM city")
    Single<List<City>> getAll();

    @Insert
    Completable insert(City city);

    @Delete
    Completable delete(City city);

}
