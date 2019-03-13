package com.baulin.alexander.weatherapp.mvp.model;

import com.baulin.alexander.weatherapp.mvp.interfaces.Model;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitAPI;
import com.baulin.alexander.weatherapp.mvp.model.retrofit.RetrofitClient;

import io.reactivex.Observable;

public class Data implements Model {
    private RetrofitAPI client = RetrofitClient.getInstance().create(RetrofitAPI.class);

    @Override
    public Observable<RootWeatherCities> getPostsFromJSON() {
        return client.getCitiesWeatherFromJSON();
    }

    @Override
    public Observable<RootWeatherCity> getWeatherCityFromJSON() {
        return client.getCityWeatherFromJSON();
    }
}
