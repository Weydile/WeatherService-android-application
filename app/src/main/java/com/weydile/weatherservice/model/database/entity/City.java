package com.weydile.weatherservice.model.database.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class City {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public City() {
    }

    @Ignore
    public City(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
