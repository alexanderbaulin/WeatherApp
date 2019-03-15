package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;

import io.reactivex.Observable;

public interface Model {

    Observable<RootWeatherCities> getCitiesWeather(double lon_left, double lat_bottom, double lon_right, double lat_top, float zoom);
    Observable<RootWeatherCity> getCurrentCityWeather();

}
