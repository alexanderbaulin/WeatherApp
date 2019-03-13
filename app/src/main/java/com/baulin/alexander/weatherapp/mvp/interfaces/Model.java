package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import io.reactivex.Observable;

public interface Model {

    Observable<RootWeatherCities> getPostsFromJSON();
    Observable<RootWeatherCity> getWeatherCityFromJSON();

}
