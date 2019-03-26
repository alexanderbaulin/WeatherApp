package com.baulin.alexander.weatherapp.mvp.interfaces;

import com.baulin.alexander.weatherapp.mvp.model.fromJSON.cities.RootWeatherCities;
import com.baulin.alexander.weatherapp.mvp.model.fromJSON.city.RootWeatherCity;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.model.LatLngBounds;

import io.reactivex.Observable;

public interface Model {
    Observable<RootWeatherCities> getCitiesWeather(LatLngBounds bounds, float zoom);
    Observable<RootWeatherCity> getCurrentCityWeather(String cityName);
    void getDeviceLocation(LocationCallback locationCallback);
    void stopDeviceLocationTracking();
}
