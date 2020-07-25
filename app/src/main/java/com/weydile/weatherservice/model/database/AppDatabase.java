package com.weydile.weatherservice.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.weydile.weatherservice.model.database.dao.CityDao;
import com.weydile.weatherservice.model.database.entity.City;

@Database(entities = {City.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao cityDao();

}
